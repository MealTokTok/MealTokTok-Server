package core.startup.mealtoktok.domain.dishstore.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class DishCategoryNameAlreadyExistsException extends DomainException {

    public static DomainException EXCEPTION = new DishCategoryNameAlreadyExistsException();

    private DishCategoryNameAlreadyExistsException() {
        super(DishStoreErrorCode.DISH_CATEGORY_NAME_ALREADY_EXISTS);
    }
}
