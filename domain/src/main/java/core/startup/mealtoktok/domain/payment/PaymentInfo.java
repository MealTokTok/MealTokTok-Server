package core.startup.mealtoktok.domain.payment;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.OrderId;

public record PaymentInfo(String paymentKey, OrderId orderId, Money amount) {

    public static PaymentInfo of(String paymentKey, String orderId, Long amount) {
        return new PaymentInfo(paymentKey, OrderId.from(orderId), Money.from(amount));
    }

    public static PaymentInfo of(String paymentKey, String orderId, Money amount) {
        return new PaymentInfo(paymentKey, OrderId.from(orderId), amount);
    }
}
