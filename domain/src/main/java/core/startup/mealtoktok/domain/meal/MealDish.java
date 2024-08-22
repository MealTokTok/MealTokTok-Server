package core.startup.mealtoktok.domain.meal;

import lombok.Builder;

@Builder
public record MealDish(Long mealDishId, Long mealId, Long dishId) {

    public static MealDish of(Long mealDishId, Long mealId, Long dishId) {
        return new MealDish(mealDishId, mealId, dishId);
    }
}
