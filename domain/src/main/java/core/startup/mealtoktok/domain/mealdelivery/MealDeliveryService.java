package core.startup.mealtoktok.domain.mealdelivery;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.VALID_DATE_TIME;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;
import core.startup.mealtoktok.domain.mealdelivery.exception.NextMealDeliveryNotFound;

@Service
@RequiredArgsConstructor
public class MealDeliveryService {

    private final MealDeliveryReader mealDeliveryReader;
    private final MealDeliveryUpdater mealDeliveryUpdater;
    private final FullDiningManager fullDiningManager;
    private final MealDeliveryCountManager mealDeliveryCountManager;

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

    public MealDelivery getNextDeliveryMeal(Long orderId) {
        List<MealDelivery> mealDeliveries = mealDeliveryReader.read(orderId);
        return mealDeliveries.stream()
                .filter(mealDelivery -> mealDelivery.getDeliveryState() == DeliveryState.PENDING)
                .findFirst()
                .orElseThrow(() -> NextMealDeliveryNotFound.EXCEPTION);
    }

    public MealDelivery getMealDelivery(TargetMealDelivery targetMealDelivery) {
        return mealDeliveryReader.read(targetMealDelivery);
    }

    public Integer countByDeliveryState(Recipient recipient, DeliveryState deliveryState) {
        return mealDeliveryReader.count(
                recipient, deliveryState, VALID_DATE_TIME, LocalDateTime.now());
    }

    public void reserveMealDelivery(TargetMealDelivery targetMealDelivery) {
        MealDelivery mealDelivery = mealDeliveryReader.read(targetMealDelivery);
        mealDeliveryUpdater.changeDeliveryState(mealDelivery, DeliveryState.DELIVERY_REQUESTED);
        // TODO :알림 발송 alarmSender.send(orderer, DeliveryState.DELIVERY_RESERVED);
    }

    public void startMealDelivery(TargetMealDelivery targetMealDelivery) {
        MealDelivery mealDelivery = mealDeliveryReader.read(targetMealDelivery);
        mealDeliveryUpdater.changeDeliveryState(mealDelivery, DeliveryState.DELIVERING);
        // TODO :알림 발송 alarmSender.send(orderer, DeliveryState.DELIVERING);
    }

    public void completeMealDelivery(TargetMealDelivery targetMealDelivery) {
        MealDelivery mealDelivery = mealDeliveryReader.read(targetMealDelivery);
        mealDeliveryUpdater.changeDeliveryState(mealDelivery, DeliveryState.DELIVERED);
        mealDeliveryCountManager.decrease(mealDelivery.getOrderId());
        // TODO :알림 발송 alarmSender.send(orderer, DeliveryState.DELIVERED);
    }

    public void changeCollectingState(
            TargetFullDining targetFullDining, CollectingState collectingState) {
        fullDiningManager.collectRequest(targetFullDining, collectingState);
        // TODO :알림 발송 alarmSender.send(targetFullDining, collectingState);
    }

    public int countCollectRequestContainers(Recipient recipient) {
        return fullDiningManager.countReturnableContainers(
                recipient, CollectingState.COLLECT_REQUESTED);
    }

    public List<FullDining> getFullDinings(Recipient recipient) {
        return fullDiningManager.getFullDinings(
                recipient, DeliveryState.DELIVERED, VALID_DATE_TIME);
    }
}
