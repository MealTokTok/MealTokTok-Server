package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;
import java.util.List;

import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.domain.order.TargetOrder;

public interface MealDeliveryRepository {

    void update(MealDelivery mealDelivery);

    List<MealDelivery> saveAll(List<MealDelivery> mealDeliveries);

    List<MealDelivery> findAll(TargetOrder targetOrder);

    MealDelivery findByOrdererAndDeliveryState(Orderer orderer, DeliveryState deliveryState);

    MealDelivery findByDeliveryStateAndTime(
            Orderer orderer,
            DeliveryState deliveryState,
            LocalDateTime startTime,
            LocalDateTime endTime);

    MealDelivery find(TargetMealDelivery targetMealDelivery);
}
