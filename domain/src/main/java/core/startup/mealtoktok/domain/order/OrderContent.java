package core.startup.mealtoktok.domain.order;

import java.util.List;

public record OrderContent(
        OrderType orderType,
        List<OrderedMeal> orderedMeals,
        String specialInstruction,
        OrderPrice orderPrice) {

    public List<MealDeliveryReservationInfo> toReservationInfos(OrderId orderId) {
        return orderedMeals.stream()
                .map(orderedMeal -> MealDeliveryReservationInfo.of(orderId, orderType, orderedMeal))
                .toList();
    }
}
