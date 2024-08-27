package core.startup.mealtoktok.domain.meal;

import java.util.List;

import core.startup.mealtoktok.domain.dishstore.Dish;

public record MealAndDishes(Meal meal, List<Dish> dishes) {
    public static MealAndDishes of(Meal meal, List<Dish> dishes) {
        return new MealAndDishes(meal, dishes);
    }
}
