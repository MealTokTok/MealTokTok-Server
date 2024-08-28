package core.startup.mealtoktok.api.payment.dto;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.OrderId;

public record PaymentRequest(String paymentKey, String orderId, Long amount) {

    public OrderId toOrderId() {
        return OrderId.from(orderId);
    }

    public Money toAmount() {
        return Money.from(amount);
    }
}
