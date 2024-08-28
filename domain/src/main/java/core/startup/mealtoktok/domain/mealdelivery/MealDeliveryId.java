package core.startup.mealtoktok.domain.mealdelivery;

import core.startup.mealtoktok.common.domain.BaseId;

public class MealDeliveryId extends BaseId<Long> {

    private MealDeliveryId(Long value) {
        super(value);
    }

    public static MealDeliveryId from(Long mealDeliveryId) {
        return new MealDeliveryId(mealDeliveryId);
    }
}
