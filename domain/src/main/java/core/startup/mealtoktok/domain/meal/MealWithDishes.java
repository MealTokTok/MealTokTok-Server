package core.startup.mealtoktok.domain.meal;

import java.util.List;

import core.startup.mealtoktok.domain.dishstore.Dish;

public record MealWithDishes(Meal meal, List<Dish> dishes) {

    public static MealWithDishes of(Meal meal, List<Dish> dishes) {
        return new MealWithDishes(meal, dishes);
    }
}
