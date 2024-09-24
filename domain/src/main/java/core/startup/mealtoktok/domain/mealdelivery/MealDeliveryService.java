package core.startup.mealtoktok.domain.mealdelivery;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.VALID_DATE_TIME;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;
import core.startup.mealtoktok.domain.mealdelivery.exception.NextMealDeliveryNotFound;
import core.startup.mealtoktok.domain.order.OrderId;

@Service
@RequiredArgsConstructor
public class MealDeliveryService {

    private final MealDeliveryReader mealDeliveryReader;
    private final MealDeliveryManager mealDeliveryManager;

    public MealDelivery getDeliveringMeal(Recipient recipient) {
        return mealDeliveryReader.read(recipient, DeliveryState.DELIVERING);
    }

    public MealDelivery getRecentDeliveredMeal(Recipient recipient) {
        return mealDeliveryReader.read(
                recipient, DeliveryState.DELIVERED, LocalDateTime.now().minusHours(1));
    }

    public SliceResult<MealDelivery> searchMealDeliveries(
            Recipient recipient, MealDeliverySearchCond cond, Cursor cursor) {
        return mealDeliveryReader.read(recipient, cond, cursor);
    }

    public List<MealDelivery> getMealDeliveries(OrderId orderId) {
        return mealDeliveryReader.read(orderId);
    }

    public MealDelivery getNextDeliveryMeal(OrderId orderId) {
        List<MealDelivery> mealDeliveries = mealDeliveryReader.read(orderId);
        return mealDeliveries.stream()
                .filter(mealDelivery -> mealDelivery.getDeliveryState() == DeliveryState.PENDING)
                .findFirst()
                .orElseThrow(() -> NextMealDeliveryNotFound.EXCEPTION);
    }

    public MealDelivery getMealDelivery(MealDeliveryId mealDeliveryId) {
        return mealDeliveryReader.read(mealDeliveryId);
    }

    public Integer countByDeliveryState(Recipient recipient, DeliveryState deliveryState) {
        return mealDeliveryReader.count(
                recipient, deliveryState, VALID_DATE_TIME, LocalDateTime.now());
    }

    public void requestDelivery(MealDeliveryId mealDeliveryId) {
        MealDelivery mealDelivery = mealDeliveryReader.read(mealDeliveryId);
        mealDeliveryManager.requestDelivery(mealDelivery);
        // TODO :알림 발송 alarmSender.send(orderer, DeliveryState.DELIVERY_RESERVED);
    }

    public void startDelivery(MealDeliveryId mealDeliveryId) {
        MealDelivery mealDelivery = mealDeliveryReader.read(mealDeliveryId);
        mealDeliveryManager.startDelivery(mealDelivery);
        // TODO :알림 발송 alarmSender.send(orderer, DeliveryState.DELIVERING);
    }

    public void completeDelivery(MealDeliveryId mealDeliveryId) {
        MealDelivery mealDelivery = mealDeliveryReader.read(mealDeliveryId);
        mealDeliveryManager.completeDelivery(mealDelivery);
        // TODO :알림 발송 alarmSender.send(orderer, DeliveryState.DELIVERED);
    }
}
