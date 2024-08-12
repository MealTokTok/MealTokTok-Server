package core.startup.mealtoktok.domain.order;

public record TargetOrder(Long orderId) {

    public static TargetOrder from(Long orderId) {
        return new TargetOrder(orderId);
    }

    public static TargetOrder from(Order order) {
        return new TargetOrder(order.getOrderId());
    }
}
