package core.startup.mealtoktok.infra.mealdelivery.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.mealdelivery.exception.MealDeliveryErrorCode;

public class FullDiningNotFoundException extends InfraException {

    public static final FullDiningNotFoundException EXCEPTION = new FullDiningNotFoundException();

    private FullDiningNotFoundException() {
        super(MealDeliveryErrorCode.FULL_DINING_NOT_FOUND);
    }
}
