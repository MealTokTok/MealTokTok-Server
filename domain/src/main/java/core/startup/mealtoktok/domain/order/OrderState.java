package core.startup.mealtoktok.domain.order;

public enum OrderState {
    PENDING,
    PAYMENT_COMPLETED,
    ORDER_ACCEPTED,
    IN_DELIVERING,
    DELIVERY_COMPLETED,
    PAYMENT_CANCELED
}
