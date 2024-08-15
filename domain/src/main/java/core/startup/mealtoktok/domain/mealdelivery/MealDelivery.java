package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MealDelivery {

    private Long mealDeliveryId;
    private Long orderId;
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

    public static MealDelivery create(Long orderId, OrderedMeal orderedMeal) {
        return new MealDelivery(
                null, orderId, orderedMeal, DeliveryState.PENDING, DeliveryDateTime.init());
    }

    public boolean hasFullDiningOption() {
        return orderedMeal.hasFullDiningOption();
    }
}
