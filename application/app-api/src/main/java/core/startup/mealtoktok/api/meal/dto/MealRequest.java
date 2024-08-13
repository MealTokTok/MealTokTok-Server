package core.startup.mealtoktok.api.meal.dto;

import core.startup.mealtoktok.domain.meal.MealInfo;
import java.util.List;

import core.startup.mealtoktok.domain.meal.MealDishes;

public record MealRequest(String mealName, int mealPrice, List<Long> dishIds) {
    public MealDishes toMealDishes() {
        return MealDishes.of(MealInfo.of(mealName, mealPrice), dishIds);
    }
}
