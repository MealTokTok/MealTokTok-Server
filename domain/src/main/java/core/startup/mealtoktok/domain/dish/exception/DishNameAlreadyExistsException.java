package core.startup.mealtoktok.domain.dish.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class DishNameAlreadyExistsException extends DomainException {

    public static DomainException EXCEPTION =new DishNameAlreadyExistsException();

    private DishNameAlreadyExistsException() {
        super(DishErrorCode.DISH_NAME_ALREADY_EXISTS);
    }
}
