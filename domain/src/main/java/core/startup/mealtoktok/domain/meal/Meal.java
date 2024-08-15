package core.startup.mealtoktok.domain.meal;

import lombok.Builder;

@Builder
public record Meal(Long mealId, MealOwner mealOwner, MealInfo mealInfo) {
    public static Meal of(MealOwner mealOwner, MealInfo mealInfo) {
        return new Meal(null, mealOwner, mealInfo);
    }

    public boolean isOwnedBy(Meal meal, MealOwner mealOwner) {
        return meal.mealOwner().isSameUser(mealOwner);
    }
}
