package core.startup.mealtoktok.domain.order;

public record MealDeliveryReservationInfo(
        OrderId orderId, OrderType orderType, OrderedMeal orderedMeal) {

    public static MealDeliveryReservationInfo of(
            OrderId orderId, OrderType orderType, OrderedMeal orderedMeal) {
        return new MealDeliveryReservationInfo(orderId, orderType, orderedMeal);
    }
}
