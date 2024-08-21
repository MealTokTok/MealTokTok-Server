package core.startup.mealtoktok.domain.mealdelivery;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealDeliveryUpdater {

    private final MealDeliveryRepository mealDeliveryRepository;

    public void changeDeliveryState(MealDelivery mealDelivery, DeliveryState deliveryState) {
        switch (deliveryState) {
            case DELIVERY_REQUESTED -> mealDelivery.deliveryRequest();
            case DELIVERING -> mealDelivery.startDelivery();
            case DELIVERED -> mealDelivery.completeDelivery();
        }
        mealDeliveryRepository.update(mealDelivery);
    }
}
