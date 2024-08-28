package core.startup.mealtoktok.api.payment.dto;

import core.startup.mealtoktok.domain.order.OrderId;

public record PaymentFailReason(String code, String message, String orderId) {

    public OrderId toOrderId() {
        return OrderId.from(orderId);
    }
}
