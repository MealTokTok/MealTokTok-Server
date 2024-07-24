package core.startup.mealtoktok.infra.dishCategory.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.dishCategory.exception.DishCategoryErrorCode;

public class DishCategoryNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new DishCategoryNotFoundException();

    private DishCategoryNotFoundException() {
        super(DishCategoryErrorCode.DISH_CATEGORY_NOT_FOUND);
    }
}