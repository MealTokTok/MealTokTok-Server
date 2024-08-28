package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;
import core.startup.mealtoktok.domain.order.OrderId;

@Component
@RequiredArgsConstructor
public class MealDeliveryReader {

    private final MealDeliveryRepository mealDeliveryRepository;

    public List<MealDelivery> read(OrderId orderId) {
        return mealDeliveryRepository.findAllByOrderId(orderId);
    }

    public MealDelivery read(MealDeliveryId mealDeliveryId) {
        return mealDeliveryRepository.findById(mealDeliveryId);
    }

    public MealDelivery read(Recipient recipient, DeliveryState deliveryState) {
        return mealDeliveryRepository.findByOrdererAndDeliveryState(recipient, deliveryState);
    }

    public MealDelivery read(
            Recipient recipient, DeliveryState deliveryState, LocalDateTime recentTime) {
        return mealDeliveryRepository.findByDeliveryStateAndTime(
                recipient, deliveryState, recentTime, LocalDateTime.now());
    }

    public SliceResult<MealDelivery> read(
            Recipient recipient, MealDeliverySearchCond cond, Cursor cursor) {
        return mealDeliveryRepository.findByCondition(recipient, cond, cursor);
    }

    public Integer count(
            Recipient recipient,
            DeliveryState deliveryState,
            LocalDateTime startTime,
            LocalDateTime endTime) {
        return mealDeliveryRepository.countByDeliveryState(
                recipient, deliveryState, startTime, endTime);
    }
}
