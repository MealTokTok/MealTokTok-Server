package core.startup.mealtoktok.domain.meal;

import java.util.List;

import core.startup.mealtoktok.domain.dishstore.DishWithImage;

public record MealWithDishes(Meal meal, List<DishWithImage> dishes) {

    public static MealWithDishes of(Meal meal, List<DishWithImage> dishes) {
        return new MealWithDishes(meal, dishes);
    }
}
