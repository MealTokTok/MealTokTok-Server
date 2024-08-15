package core.startup.mealtoktok.domain.meal;

import java.util.List;

public record MealAndDishIds(Meal meal, List<Long> dishIds) {
    public static MealAndDishIds create(Meal meal, List<Long> dishIds) {
        return new MealAndDishIds(meal, dishIds);
    }
}
