package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.meal.exception.MealNameAlreadyExitsException;
import core.startup.mealtoktok.domain.meal.exception.MealOwnerNotMatchException;

@Component
@RequiredArgsConstructor
public class MealValidator {

    private final MealRepository mealRepository;

    public void validate(MealContent createMealContent) {
        checkMealNameExists(createMealContent.mealInfo().mealName());
    }

    public void validate(MealOwner mealOwner, Meal meal, MealContent updatedMealContent) {
        checkOwnership(meal, mealOwner);
        checkMealNameExistsExcludingTarget(meal, updatedMealContent.mealInfo().mealName());
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

    private void checkOwnership(Meal meal, MealOwner mealOwner) {
        if (!meal.isOwnedBy(meal, mealOwner)) {
            throw MealOwnerNotMatchException.EXCEPTION;
        }
    }
}
