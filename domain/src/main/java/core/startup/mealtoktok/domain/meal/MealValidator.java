package core.startup.mealtoktok.domain.meal;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.DishReader;
import core.startup.mealtoktok.domain.dishstore.TargetDish;
import core.startup.mealtoktok.domain.meal.exception.DishSoldOutException;
import core.startup.mealtoktok.domain.meal.exception.InvalidDishCountException;
import core.startup.mealtoktok.domain.meal.exception.MealNameAlreadyExitsException;
import core.startup.mealtoktok.domain.meal.exception.MealOwnerNotMatchException;

@Component
@RequiredArgsConstructor
public class MealValidator {

    private final MealRepository mealRepository;
    private final DishReader dishReader;

    public void validate(MealContent mealContent) {
        checkMealNameExists(mealContent.mealInfo().mealName());
        checkDishCount(mealContent.dishIds());
        checkDishSoldOut(mealContent.dishIds());
    }

    public void validate(MealOwner mealOwner, Meal meal, MealContent mealContent) {
        checkOwnership(meal, mealOwner);
        checkMealNameExistsExcludingTarget(meal, mealContent.mealInfo().mealName());
        checkDishCount(mealContent.dishIds());
    }

    public void validate(MealOwner mealOwner, Meal meal) {
        checkOwnership(meal, mealOwner);
    }

    private void checkMealNameExists(String mealName) {
        if (mealRepository.exitsByMealName(mealName)) {
            throw MealNameAlreadyExitsException.EXCEPTION;
        }
    }

    private void checkMealNameExistsExcludingTarget(Meal meal, String mealName) {
        if (mealRepository.exitsByNameExcludingTargetMeal(meal, mealName)) {
            throw MealNameAlreadyExitsException.EXCEPTION;
        }
    }

    private void checkDishCount(List<Long> dishIds) {
        if (dishIds.size() != 4) {
            throw InvalidDishCountException.EXCEPTION;
        }
    }

    private void checkDishSoldOut(List<Long> dishIds) {
        dishIds.forEach(
                dishId -> {
                    if (dishReader.read(TargetDish.from(dishId)).isSoldOut()) {
                        throw DishSoldOutException.EXCEPTION;
                    }
                });
    }

    private void checkOwnership(Meal meal, MealOwner mealOwner) {
        if (!meal.isOwnedBy(meal, mealOwner)) {
            throw MealOwnerNotMatchException.EXCEPTION;
        }
    }
}
