package core.startup.mealtoktok.infra.dish.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.dish.exception.DishErrorCode;

public class DishNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new DishNotFoundException();

    private DishNotFoundException() {
        super(DishErrorCode.DISH_NOT_FOUND);
    }
}