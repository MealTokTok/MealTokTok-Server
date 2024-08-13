package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MealService {

    private final MealReader mealReader;
    private final MealUpdater mealUpdater;
    private final MealAppender mealAppender;

    public void createMeal(MealDishes mealDishes) {
        mealAppender.append(mealDishes);
    }

    public void updateMeal(TargetMeal targetMeal, MealDishes mealDishes) {
        Meal meal = mealReader.read(targetMeal);
        mealUpdater.update(meal, mealDishes);
    }
}
