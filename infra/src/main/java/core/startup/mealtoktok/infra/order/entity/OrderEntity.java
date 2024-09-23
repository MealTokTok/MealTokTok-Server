package core.startup.mealtoktok.infra.order.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.order.OrderState;
import core.startup.mealtoktok.domain.order.OrderType;
import core.startup.mealtoktok.infra.jpa.entity.BaseTimeEntity;

@Entity
@SuperBuilder
@AllArgsConstructor
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseTimeEntity {

    @Id private String orderId;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    private String specialInstruction;

    @Embedded private OrdererVO orderer;

    @Embedded private OrderPriceVO orderPrice;

    private Long deliveryAddressId;

    private Integer totalMealDeliveryCount;

    private Integer remainingMealDeliveryCount;

    private LocalDateTime cancelledAt;

    public static OrderEntity from(Order order) {
        return OrderEntity.builder()
                .orderId(order.getOrderId().getValue())
                .orderType(order.getOrderType())
                .orderState(order.getOrderState())
                .specialInstruction(order.getSpecialInstruction())
                .orderer(OrdererVO.from(order.getOrderer()))
                .orderPrice(OrderPriceVO.from(order.getOrderPrice()))
                .deliveryAddressId(order.getDeliveryAddressId())
                .totalMealDeliveryCount(order.getTotalMealDeliveryCount())
                .remainingMealDeliveryCount(order.getRemainingMealDeliveryCount())
                .cancelledAt(order.getCancelledAt())
                .createdAt(order.getOrderedAt())
                .build();
    }

    public Order toDomain() {
        return Order.builder()
                .orderId(OrderId.from(orderId))
                .orderType(orderType)
                .specialInstruction(specialInstruction)
                .orderState(orderState)
                .orderer(orderer.toDomain())
                .orderPrice(orderPrice.toDomain())
                .deliveryAddressId(deliveryAddressId)
                .totalMealDeliveryCount(totalMealDeliveryCount)
                .remainingMealDeliveryCount(remainingMealDeliveryCount)
                .cancelledAt(cancelledAt)
                .orderedAt(createdAt)
                .build();
    }

    public void update(Order order) {
        this.orderState = order.getOrderState();
        this.remainingMealDeliveryCount = order.getRemainingMealDeliveryCount();
        this.cancelledAt = order.getCancelledAt();
    }
}
