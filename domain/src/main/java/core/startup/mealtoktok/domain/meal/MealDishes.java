package core.startup.mealtoktok.domain.meal;

import java.util.List;

public record MealDishes(MealInfo mealInfo, List<Long> dishIds) {
    public static MealDishes of(MealInfo mealInfo, List<Long> dishIds) {
        return new MealDishes(mealInfo, dishIds);
    }
}
