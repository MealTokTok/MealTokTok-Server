package core.startup.mealtoktok.domain.meal;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.Dish;
import core.startup.mealtoktok.domain.dishstore.DishReader;
import core.startup.mealtoktok.domain.dishstore.TargetDish;

@Component
@RequiredArgsConstructor
public class MealWithDishesFinder {

    private final MealDishReader mealDishReader;
    private final DishReader dishReader;

    public MealWithDishes find(Meal meal) {

        List<Dish> dishes =
                mealDishReader.read(TargetMeal.from(meal.getMealId())).stream()
                        .map(mealDish -> dishReader.read(TargetDish.from(mealDish.dishId())))
                        .toList();

        return MealWithDishes.of(meal, dishes);
    }
}
