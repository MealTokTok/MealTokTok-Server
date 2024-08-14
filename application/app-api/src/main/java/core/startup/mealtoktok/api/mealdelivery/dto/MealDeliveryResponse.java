package core.startup.mealtoktok.api.mealdelivery.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

import core.startup.mealtoktok.domain.mealdelivery.DeliveryDateTime;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.OrderedMeal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)
public record MealDeliveryResponse(
        Long mealDeliveryId,
        Long orderId,
        OrderedMeal orderedMeal,
        DeliveryState deliveryState,
        DeliveryDateTime deliveryDateTime) {

    public static MealDeliveryResponse from(MealDelivery mealDelivery) {
        return new MealDeliveryResponse(
                mealDelivery.getMealDeliveryId(),
                mealDelivery.getOrderId(),
                mealDelivery.getOrderedMeal(),
                mealDelivery.getDeliveryState(),
                mealDelivery.getDeliveryDateTime());
    }
}
