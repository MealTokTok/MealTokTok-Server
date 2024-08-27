package core.startup.mealtoktok.infra.toss.dto;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.OrderId;

public record TossConfirmRequest(String paymentKey, String orderId, String amount) {

    public static TossConfirmRequest of(String paymentKey, OrderId orderId, Money amount) {
        return new TossConfirmRequest(paymentKey, orderId.value(), amount.toString());
    }
}
