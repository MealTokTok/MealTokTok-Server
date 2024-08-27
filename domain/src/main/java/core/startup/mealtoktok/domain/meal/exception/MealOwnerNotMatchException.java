package core.startup.mealtoktok.domain.meal.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class MealOwnerNotMatchException extends DomainException {

    public static DomainException EXCEPTION = new MealOwnerNotMatchException();

    private MealOwnerNotMatchException() {
        super(MealErrorCode.MEAL_OWNER_NOT_MATCH);
    }
}
