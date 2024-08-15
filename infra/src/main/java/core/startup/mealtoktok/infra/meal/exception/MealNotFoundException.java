package core.startup.mealtoktok.infra.meal.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.meal.exception.MealErrorCode;

public class MealNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new MealNotFoundException();

    private MealNotFoundException() {
        super(MealErrorCode.MEAL_NOT_FOUND);
    }
}
