package core.startup.mealtoktok.domain.meal;

import lombok.Builder;

@Builder
public record MealDish(Long mealDishId, Long mealId, Long dishId) {}
