package core.startup.mealtoktok.domain.meal;

public interface MealRepository {

    boolean exitsByMealName(String mealName);

    void save(MealDishes mealDishes);

    Meal findById(TargetMeal targetMeal);

    void update(Meal meal, MealDishes mealDishes);
}
