package core.startup.mealtoktok.domain.order;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.exception.OrderDomainException;
import core.startup.mealtoktok.domain.user.DeliveryAddress;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private OrderId orderId;
    private OrderType orderType;
    private OrderState orderState;
    private String specialInstruction;
    private Orderer orderer;
    private OrderPrice orderPrice;
    private Long deliveryAddressId;
    private Integer totalMealDeliveryCount;
    private Integer remainingMealDeliveryCount;
    private LocalDateTime orderedAt;
    private LocalDateTime cancelledAt;

    public static Order create(
            OrderId orderId,
            Orderer orderer,
            OrderType orderType,
            String specialInstruction,
            OrderPrice orderPrice,
            DeliveryAddress deliveryAddress,
            Integer mealDeliverySize) {
        return Order.builder()
                .orderId(orderId)
                .orderType(orderType)
                .orderState(OrderState.PENDING)
                .specialInstruction(specialInstruction)
                .orderer(orderer)
                .orderPrice(orderPrice)
                .deliveryAddressId(deliveryAddress.getDeliveryAddressId())
                .totalMealDeliveryCount(mealDeliverySize)
                .remainingMealDeliveryCount(mealDeliverySize)
                .build();
    }

    public void completePayment() {
        this.orderState = OrderState.PAYMENT_COMPLETED;
    }

    public void accept() {
        if (this.orderState != OrderState.PAYMENT_COMPLETED) {
            throw new OrderDomainException("결제가 완료된 주문만 접수할 수 있습니다.");
        }

        this.orderState = OrderState.ORDER_ACCEPTED;
    }

    public void cancelPayment() {
        if (this.orderState.equals(OrderState.IN_DELIVERING)
                || this.orderState.equals(OrderState.DELIVERY_COMPLETED)) {
            throw new OrderDomainException("배달 중인 주문은 결제를 취소할 수 없습니다.");
        }
        this.orderState = OrderState.PAYMENT_CANCELED;
        this.cancelledAt = LocalDateTime.now();
    }

    public void startDelivery() {
        if (this.orderState != OrderState.ORDER_ACCEPTED) {
            throw new OrderDomainException("주문이 접수된 뒤에 배달을 시작할 수 있습니다.");
        }

        if (isFirstDelivery()) {
            this.orderState = OrderState.IN_DELIVERING;
        }
    }

    public void completeDelivery() {
        if (this.orderState != OrderState.IN_DELIVERING) {
            throw new OrderDomainException("배달 중인 주문만 배달 완료처리할 수 있습니다.");
        }

        this.orderState = OrderState.DELIVERY_COMPLETED;
    }

    public boolean isOrderBy(Orderer orderer) {
        return this.orderer.isSameUser(orderer);
    }

    public void completeOneDelivery() {
        this.remainingMealDeliveryCount--;
        if (this.remainingMealDeliveryCount == 0) {
            completeDelivery();
        }
    }

    private boolean isFirstDelivery() {
        return this.totalMealDeliveryCount.equals(this.remainingMealDeliveryCount);
    }
}
