package core.startup.mealtoktok.infra.dish.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.dish.exception.DishErrorCode;

public class DishStoreNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new DishStoreNotFoundException();

    private DishStoreNotFoundException() {
        super(DishErrorCode.DISH_STORE_NOT_FOUND);
    }
}
