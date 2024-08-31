package core.startup.mealtoktok.domain.meal;

import java.util.List;

import core.startup.mealtoktok.domain.dishstore.DishAndImage;

public record MealAndDishes(Meal meal, List<DishAndImage> dishes) {
    public static MealAndDishes of(Meal meal, List<DishAndImage> dishes) {
        return new MealAndDishes(meal, dishes);
    }
}
