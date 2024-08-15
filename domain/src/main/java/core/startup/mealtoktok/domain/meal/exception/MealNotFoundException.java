package core.startup.mealtoktok.domain.meal.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class MealNotFoundException extends DomainException {

    public static DomainException EXCEPTION = new MealNotFoundException();

    private MealNotFoundException() {
        super(MealErrorCode.MEAL_NOT_FOUND);
    }
}
