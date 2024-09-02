package core.startup.mealtoktok.domain.meal;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealReader mealReader;
    private final MealUpdater mealUpdater;
    private final MealAppender mealAppender;
    private final MealRemover mealRemover;
    private final MealDishReader mealDishReader;
    private final MealWithDishesFinder mealWithDishesFinder;

    @Transactional
    public void createMeal(MealOwner mealOwner, MealContent newMealContent) {
        mealAppender.append(mealOwner, newMealContent);
    }

    @Transactional
    public void updateMeal(
            MealOwner mealOwner, TargetMeal targetMeal, MealContent updatedMealContent) {
        Meal meal = mealReader.read(targetMeal);
        List<MealDish> mealDishes = mealDishReader.read(targetMeal);
        mealUpdater.update(mealOwner, meal, mealDishes, updatedMealContent);
    }

    @Transactional
    public void deleteMeal(MealOwner mealOwner, TargetMeal targetMeal) {
        Meal meal = mealReader.read(targetMeal);
        List<MealDish> mealDishes = mealDishReader.read(targetMeal);
        mealRemover.remove(mealOwner, meal, mealDishes);
    }

    public MealWithDishes readMealAndDishes(TargetMeal targetMeal) {
        Meal meal = mealReader.read(targetMeal);
        return mealWithDishesFinder.find(meal);
    }

    public List<MealWithDishes> readMealAndDishes(MealOwner mealOwner) {
        return mealReader.read(mealOwner).stream().map(mealWithDishesFinder::find).toList();
    }
}
