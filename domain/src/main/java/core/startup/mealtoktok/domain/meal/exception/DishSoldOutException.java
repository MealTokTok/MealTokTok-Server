package core.startup.mealtoktok.domain.meal.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class DishSoldOutException extends DomainException {

    public static DomainException EXCEPTION = new DishSoldOutException();

    private DishSoldOutException() {
        super(MealErrorCode.DISH_SOLD_OUT);
    }
}
