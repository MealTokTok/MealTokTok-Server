package core.startup.mealtoktok.infra.order.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.OrderState;
import core.startup.mealtoktok.domain.order.OrderType;
import core.startup.mealtoktok.infra.jpa.config.BaseTimeEntity;

@Entity
@SuperBuilder
@AllArgsConstructor
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    private String specialInstruction;

    @Embedded private OrdererVO orderer;

    @Embedded private OrderPriceVO orderPrice;

    public static OrderEntity from(Order order) {
        return OrderEntity.builder()
                .orderType(order.getOrderType())
                .orderState(order.getOrderState())
                .specialInstruction(order.getSpecialInstruction())
                .orderer(OrdererVO.from(order.getOrderer()))
                .orderPrice(OrderPriceVO.from(order.getOrderPrice()))
                .createdAt(order.getOrderTime())
                .build();
    }

    public Order toDomain() {
        return Order.builder()
                .orderId(orderId)
                .orderType(orderType)
                .specialInstruction(specialInstruction)
                .orderState(orderState)
                .orderer(orderer.toDomain())
                .orderPrice(orderPrice.toDomain())
                .orderTime(createdAt)
                .build();
    }
}
