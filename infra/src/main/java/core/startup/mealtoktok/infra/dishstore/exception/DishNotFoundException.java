package core.startup.mealtoktok.infra.dishstore.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.dishstore.exception.DishStoreErrorCode;

public class DishNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new DishNotFoundException();

    private DishNotFoundException() {
        super(DishStoreErrorCode.DISH_NOT_FOUND);
    }
}