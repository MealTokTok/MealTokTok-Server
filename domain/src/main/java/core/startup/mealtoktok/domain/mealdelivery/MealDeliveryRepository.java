package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;
import java.util.List;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;

public interface MealDeliveryRepository {

    void update(MealDelivery mealDelivery);

    List<MealDelivery> saveAll(List<MealDelivery> mealDeliveries);

    List<MealDelivery> findAll(String orderId);

    MealDelivery findByOrdererAndDeliveryState(Recipient recipient, DeliveryState deliveryState);

    MealDelivery findByDeliveryStateAndTime(
            Recipient recipient,
            DeliveryState deliveryState,
            LocalDateTime startTime,
            LocalDateTime endTime);

    MealDelivery find(TargetMealDelivery targetMealDelivery);

    SliceResult<MealDelivery> findByCondition(
            Recipient recipient, MealDeliverySearchCond cond, Cursor cursor);

    Integer countByDeliveryState(
            Recipient recipient,
            DeliveryState deliveryState,
            LocalDateTime startTime,
            LocalDateTime endTime);
}
