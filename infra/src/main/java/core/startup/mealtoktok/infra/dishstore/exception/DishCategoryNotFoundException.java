package core.startup.mealtoktok.infra.dishstore.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.dishstore.exception.DishStoreErrorCode;

public class DishCategoryNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new DishCategoryNotFoundException();

    private DishCategoryNotFoundException() {
        super(DishStoreErrorCode.DISH_CATEGORY_NOT_FOUND);
    }
}
