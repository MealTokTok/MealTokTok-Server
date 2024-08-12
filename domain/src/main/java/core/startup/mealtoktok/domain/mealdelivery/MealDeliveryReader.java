package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.domain.order.TargetOrder;

@Component
@RequiredArgsConstructor
public class MealDeliveryReader {

    private final MealDeliveryRepository mealDeliveryRepository;

    public List<MealDelivery> read(TargetOrder targetOrder) {
        return mealDeliveryRepository.findAll(targetOrder);
    }

    public MealDelivery read(TargetMealDelivery targetMealDelivery) {
        return mealDeliveryRepository.find(targetMealDelivery);
    }

    public MealDelivery read(Orderer orderer, DeliveryState deliveryState) {
        return mealDeliveryRepository.findByOrdererAndDeliveryState(orderer, deliveryState);
    }

    public MealDelivery read(
            Orderer orderer, DeliveryState deliveryState, LocalDateTime recentTime) {
        return mealDeliveryRepository.findByDeliveryStateAndTime(
                orderer, deliveryState, recentTime, LocalDateTime.now());
    }
}
