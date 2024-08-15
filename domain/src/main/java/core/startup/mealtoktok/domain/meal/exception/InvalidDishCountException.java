package core.startup.mealtoktok.domain.meal.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class InvalidDishCountException extends DomainException {

    public static DomainException EXCEPTION = new InvalidDishCountException();

    private InvalidDishCountException() {
        super(MealErrorCode.INVALID_DISH_COUNT);
    }
}
