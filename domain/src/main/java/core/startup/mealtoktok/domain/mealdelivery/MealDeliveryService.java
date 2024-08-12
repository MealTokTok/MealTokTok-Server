package core.startup.mealtoktok.domain.mealdelivery;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.VALID_DATE_TIME;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.Orderer;

@Service
@RequiredArgsConstructor
public class MealDeliveryService {

    private final MealDeliveryReader mealDeliveryReader;
    private final MealDeliveryUpdater mealDeliveryUpdater;
    private final FullDiningManager fullDiningManager;

    public MealDelivery getDeliveringMeal(Orderer orderer) {
        return mealDeliveryReader.read(orderer, DeliveryState.DELIVERING);
    }

    public MealDelivery getRecentDeliveredMeal(Orderer orderer) {
        return mealDeliveryReader.read(
                orderer, DeliveryState.DELIVERED, LocalDateTime.now().minusHours(1));
    }

    public MealDelivery getMealDelivery(TargetMealDelivery targetMealDelivery) {
        return mealDeliveryReader.read(targetMealDelivery);
    }

    public void changeDeliveryState(
            TargetMealDelivery targetMealDelivery, DeliveryState deliveryState) {
        MealDelivery mealDelivery = mealDeliveryReader.read(targetMealDelivery);
        mealDeliveryUpdater.changeDeliveryState(mealDelivery, deliveryState);
        // TODO :알림 발송 alarmSender.send(orderer, deliveryState);
    }

    public void changeCollectingState(
            TargetFullDining targetFullDining, CollectingState collectingState) {
        fullDiningManager.collectRequest(targetFullDining, collectingState);
    }

    public int countCollectRequestContainers(Orderer orderer) {
        return fullDiningManager.countReturnableContainers(
                orderer, CollectingState.COLLECT_REQUESTED);
    }

    public List<FullDining> getFullDinings(Orderer orderer) {
        return fullDiningManager.getFullDinings(orderer, DeliveryState.DELIVERED, VALID_DATE_TIME);
    }
}
