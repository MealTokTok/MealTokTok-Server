package core.startup.mealtoktok.api.meal.dto;

import core.startup.mealtoktok.domain.meal.Meal;
import core.startup.mealtoktok.domain.order.Money;

public record MealResponse(Long mealId, String mealName, Money mealPrice) {
    public static MealResponse from(Meal meal) {
        return new MealResponse(
                meal.mealId(), meal.mealInfo().mealName(), meal.mealInfo().mealPrice());
    }
}
