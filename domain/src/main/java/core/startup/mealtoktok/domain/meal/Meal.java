package core.startup.mealtoktok.domain.meal;

import lombok.Builder;

@Builder
public record Meal(
        Long mealId,
        MealInfo mealInfo
) {

}
