package core.startup.mealtoktok.infra.mealdelivery.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.mealdelivery.exception.MealDeliveryErrorCode;

public class MealDeliveryNotFoundException extends InfraException {

    public static final MealDeliveryNotFoundException EXCEPTION =
            new MealDeliveryNotFoundException();

    private MealDeliveryNotFoundException() {
        super(MealDeliveryErrorCode.MEAL_DELIVERY_NOT_FOUND);
    }
}
