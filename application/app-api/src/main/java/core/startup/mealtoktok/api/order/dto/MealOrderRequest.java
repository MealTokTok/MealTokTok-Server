package core.startup.mealtoktok.api.order.dto;

import java.util.List;

import core.startup.mealtoktok.domain.order.OrderContent;
import core.startup.mealtoktok.domain.order.OrderPrice;
import core.startup.mealtoktok.domain.order.OrderType;
import core.startup.mealtoktok.domain.order.OrderedMeal;

public record MealOrderRequest(
        OrderType orderType,
        List<OrderedMeal> orderedMeals,
        String specialInstruction,
        int mealPrice,
        int deliveryPrice,
        int fullServicePrice,
        int totalPrice) {

    public OrderContent toContent() {
        return new OrderContent(
                orderType,
                orderedMeals,
                specialInstruction,
                OrderPrice.of(mealPrice, deliveryPrice, fullServicePrice, totalPrice));
    }
}
