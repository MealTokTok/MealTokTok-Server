package core.startup.mealtoktok.domain.meal;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.DishWithImage;
import core.startup.mealtoktok.domain.dishstore.DishWithImageFinder;
import core.startup.mealtoktok.domain.dishstore.TargetDish;

@Component
@RequiredArgsConstructor
public class MealWithDishesFinder {

    private final MealDishReader mealDishReader;
    private final DishWithImageFinder dishWithImageFinder;

    public MealWithDishes find(Meal meal) {
        List<DishWithImage> dishes =
                mealDishReader.read(TargetMeal.from(meal.getMealId())).stream()
                        .map(
                                mealDish ->
                                        dishWithImageFinder.find(
                                                TargetDish.from(mealDish.dishId())))
                        .toList();

        return MealWithDishes.of(meal, dishes);
    }
}
