package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealDeliveryUpdater {

    private final MealDeliveryRepository mealDeliveryRepository;

    public void changeDeliveryState(MealDelivery mealDelivery, DeliveryState deliveryState) {
        switch (deliveryState) {
            case DELIVERING -> mealDelivery.startDelivery(LocalDateTime.now());
            case DELIVERED -> mealDelivery.completeDelivery(LocalDateTime.now());
        }
        mealDeliveryRepository.update(mealDelivery);
    }
}
