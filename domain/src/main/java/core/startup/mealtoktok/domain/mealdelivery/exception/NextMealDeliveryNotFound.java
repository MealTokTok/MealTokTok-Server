package core.startup.mealtoktok.domain.mealdelivery.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class NextMealDeliveryNotFound extends DomainException {

    public static final NextMealDeliveryNotFound EXCEPTION = new NextMealDeliveryNotFound();

    private NextMealDeliveryNotFound() {
        super(MealDeliveryErrorCode.NEXT_MEAL_DELIVERY_NOT_FOUND);
    }
}
