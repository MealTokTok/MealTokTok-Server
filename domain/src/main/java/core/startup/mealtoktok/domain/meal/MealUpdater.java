package core.startup.mealtoktok.domain.meal;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealUpdater {

    private final MealValidator mealValidator;
    private final MealRepository mealRepository;

    public void update(
            MealOwner mealOwner,
            Meal meal,
            List<MealDish> mealDishes,
            MealContent updatedMealContent) {
        mealValidator.validate(mealOwner, meal, updatedMealContent);
        mealRepository.update(meal, updatedMealContent);

        IntStream.range(0, mealDishes.size())
                .forEach(
                        i -> {
                            MealDish mealDish = mealDishes.get(i);
                            Long dishId = updatedMealContent.dishIds().get(i);
                            mealRepository.updateMealDish(mealDish, dishId);
                        });
    }
}
