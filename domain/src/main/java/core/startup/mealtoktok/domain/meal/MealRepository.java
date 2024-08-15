package core.startup.mealtoktok.domain.meal;

import java.util.List;

public interface MealRepository {

    boolean exitsByMealName(String mealName);

    void save(MealAndDishIds mealAndDishIds);

    Meal findById(TargetMeal targetMeal);

    void update(Meal meal, List<MealDish> mealDishes, MealContent mealContent);

    void delete(Meal meal, List<MealDish> mealDishes);

    List<MealDish> findAllByMealId(TargetMeal targetMeal);

    boolean exitsByNameExcludingTargetMeal(Meal meal, String mealName);

    List<Meal> findAllByMealOwner(MealOwner mealOwner);
}
