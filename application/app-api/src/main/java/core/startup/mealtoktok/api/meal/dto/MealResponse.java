package core.startup.mealtoktok.api.meal.dto;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.meal.Meal;

public record MealResponse(Long mealId, String mealName, Money mealPrice) {

    public static MealResponse from(Meal meal) {
        return new MealResponse(
                meal.getMealId(), meal.getMealInfo().mealName(), meal.getMealInfo().mealPrice());
    }
}
