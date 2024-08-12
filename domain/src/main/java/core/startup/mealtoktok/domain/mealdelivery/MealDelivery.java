package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import core.startup.mealtoktok.domain.order.OrderedMeal;
import core.startup.mealtoktok.domain.order.TargetOrder;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MealDelivery {

    private Long mealDeliveryId;
    private TargetOrder targetOrder;
    private OrderedMeal orderedMeal;
    private DeliveryState deliveryState;
    private DeliveryDateTime deliveryDateTime;

    public void startDelivery(LocalDateTime startDateTime) {
        this.deliveryDateTime = DeliveryDateTime.start(startDateTime);
        this.deliveryState = DeliveryState.DELIVERING;
    }

    public void completeDelivery(LocalDateTime completeDateTime) {
        this.deliveryDateTime =
                DeliveryDateTime.complete(deliveryDateTime.deliveryStartTime(), completeDateTime);
        this.deliveryState = DeliveryState.DELIVERED;
    }

    public static MealDelivery create(TargetOrder targetOrder, OrderedMeal orderedMeal) {
        return new MealDelivery(
                null, targetOrder, orderedMeal, DeliveryState.PENDING, DeliveryDateTime.init());
    }

    public boolean hasFullDiningOption() {
        return orderedMeal.hasFullDiningOption();
    }
}
