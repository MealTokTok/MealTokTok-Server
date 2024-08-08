package core.startup.mealtoktok.domain.dishstore.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class DishStoreNameAlreadyExitsException extends DomainException {

    public static DomainException EXCEPTION = new DishStoreNameAlreadyExitsException();

    private DishStoreNameAlreadyExitsException() {
        super(DishStoreErrorCode.DISH_STORE_NAME_ALREADY_EXISTS);
    }
}
