package core.startup.mealtoktok.infra.dishStore.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.DishStore.exception.DishStoreErrorCode;


public class DishStoreNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new DishStoreNotFoundException();

    private DishStoreNotFoundException() {
        super(DishStoreErrorCode.DISH_STORE_NOT_FOUND);
    }
}
