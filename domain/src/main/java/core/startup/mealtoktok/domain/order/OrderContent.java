package core.startup.mealtoktok.domain.order;

import java.util.List;

import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.OrderedMeal;

public record OrderContent(
        OrderType orderType,
        List<OrderedMeal> orderedMeals,
        String specialInstruction,
        OrderPrice orderPrice) {

    public List<MealDelivery> toMealDeliveries(TargetOrder targetOrder) {
        return orderedMeals.stream()
                .map(
                        orderedMeal ->
                                MealDelivery.create(
                                        targetOrder.orderId(),
                                        orderType.toOrderTypeForDelivery(),
                                        orderedMeal))
                .toList();
    }
}
