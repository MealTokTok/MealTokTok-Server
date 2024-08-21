package core.startup.mealtoktok.domain.order;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.exception.OrderDomainException;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private Long orderId;
    private OrderType orderType;
    private OrderState orderState;
    private String specialInstruction;
    private Orderer orderer;
    private OrderPrice orderPrice;
    private Integer totalMealDeliveryCount;
    private Integer remainingMealDeliveryCount;
    private LocalDateTime orderTime;

    public static Order create(
            Orderer orderer,
            OrderType orderType,
            String specialInstruction,
            OrderPrice orderPrice,
            Integer mealDeliverySize) {
        return Order.builder()
                .orderType(orderType)
                .orderState(OrderState.PENDING)
                .specialInstruction(specialInstruction)
                .orderer(orderer)
                .orderPrice(orderPrice)
                .totalMealDeliveryCount(mealDeliverySize)
                .remainingMealDeliveryCount(mealDeliverySize)
                .build();
    }

    public void cancelPayment() {
        if (this.orderState != OrderState.PENDING) {
            throw new OrderDomainException("결제가 완료된 주문은 취소할 수 없습니다.");
        }
        this.orderState = OrderState.PAYMENT_CANCELED;
    }

    public void completePayment() {
        this.orderState = OrderState.PAYMENT_COMPLETED;
    }

    public void acceptOrder() {
        if (this.orderState != OrderState.PAYMENT_COMPLETED) {
            throw new OrderDomainException("결제가 완료된 주문만 접수할 수 있습니다.");
        }

        this.orderState = OrderState.ORDER_ACCEPTED;
    }

    public void completeDelivery() {
        if (this.orderState != OrderState.PAYMENT_COMPLETED) {
            throw new OrderDomainException("주문이 완료된 뒤에 배달을 완료할 수 있습니다.");
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
}
