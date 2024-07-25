package core.startup.mealtoktok.domain.dish.exception;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.DomainException;
import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.dishCategory.exception.DishCategoryErrorCode;

public class DishNameAlreadyExistsException extends DomainException {

    public static DomainException EXCEPTION =new DishNameAlreadyExistsException();

    public DishNameAlreadyExistsException() {
        super(DishErrorCode.DISH_NAME_ALREADY_EXISTS);
    }
}
