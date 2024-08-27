package core.startup.mealtoktok.domain.order;

public record OrderId(String value) {

    public static OrderId from(String orderId) {
        return new OrderId(orderId);
    }
}
