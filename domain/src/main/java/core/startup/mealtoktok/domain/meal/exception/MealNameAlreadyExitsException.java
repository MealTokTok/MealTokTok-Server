package core.startup.mealtoktok.domain.meal.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class MealNameAlreadyExitsException extends DomainException {

    public static DomainException EXCEPTION = new MealNameAlreadyExitsException();

    private MealNameAlreadyExitsException() {
        super(MealErrorCode.MEAL_NAME_ALREADY_EXISTS);
    }
}
