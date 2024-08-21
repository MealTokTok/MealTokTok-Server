package core.startup.mealtoktok.domain.mealdelivery.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class MealDeliveryDomainException extends DomainException {

    public MealDeliveryDomainException(String message) {
        super(MealDeliveryErrorCode.MEAL_DELIVERY_DOMAIN_ERROR.setMessage(message));
    }
}
