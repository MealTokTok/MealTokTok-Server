package core.startup.mealtoktok.infra.dish.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.dish.exception.DishErrorCode;

public class DishCategoryNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new DishCategoryNotFoundException();

    private DishCategoryNotFoundException() {
        super(DishErrorCode.DISH_CATEGORY_NOT_FOUND);
    }
}