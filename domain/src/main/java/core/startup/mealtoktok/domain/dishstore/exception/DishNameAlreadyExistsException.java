package core.startup.mealtoktok.domain.dishstore.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class DishNameAlreadyExistsException extends DomainException {

    public static DomainException EXCEPTION =new DishNameAlreadyExistsException();

    private DishNameAlreadyExistsException() {
        super(DishStoreErrorCode.DISH_NAME_ALREADY_EXISTS);
    }
}
