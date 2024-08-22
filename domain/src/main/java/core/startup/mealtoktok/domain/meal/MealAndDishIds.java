package core.startup.mealtoktok.domain.meal;

import java.util.List;

import core.startup.mealtoktok.domain.meal.exception.InvalidDishCountException;

public record MealAndDishIds(Meal meal, List<Long> dishIds) {
    public static MealAndDishIds create(Meal meal, List<Long> dishIds) {
        checkDishCount(dishIds);
        return new MealAndDishIds(meal, dishIds);
    }

    private static void checkDishCount(List<Long> dishIds) {
        if (dishIds.size() != 4) {
            throw InvalidDishCountException.EXCEPTION;
        }
    }

    public void update(MealAndDishIds mealAndDishIds) {
        this.dishIds.clear();
        this.dishIds.addAll(mealAndDishIds.dishIds());
    }
}
