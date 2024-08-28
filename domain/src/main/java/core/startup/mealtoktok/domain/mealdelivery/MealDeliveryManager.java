package core.startup.mealtoktok.domain.mealdelivery;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.FullDiningInfo;
import core.startup.mealtoktok.domain.order.MealDeliveryReservationInfo;
import core.startup.mealtoktok.domain.order.MealDeliveryReserver;
import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.OrderManager;
import core.startup.mealtoktok.domain.order.OrderReader;

@Component
@RequiredArgsConstructor
public class MealDeliveryManager implements MealDeliveryReserver {

    private final MealDeliveryRepository mealDeliveryRepository;
    private final OrderReader orderReader;
    private final OrderManager orderManager;

    @Override
    public List<FullDiningInfo> reserve(List<MealDeliveryReservationInfo> reservationInfos) {
        List<MealDelivery> mealDeliveries =
                reservationInfos.stream().map(MealDelivery::create).toList();

        List<MealDelivery> saved = mealDeliveryRepository.saveAll(mealDeliveries);
        return toMealDeliveryInfos(saved);
    }

    public void requestDelivery(MealDelivery mealDelivery) {
        mealDelivery.requestDelivery();
        mealDeliveryRepository.update(mealDelivery);
    }

    public void startDelivery(MealDelivery mealDelivery) {
        Order order = orderReader.read(mealDelivery.getOrderId());
        orderManager.startDelivery(order);
        mealDelivery.startDelivery();
        mealDeliveryRepository.update(mealDelivery);
    }

    public void completeDelivery(MealDelivery mealDelivery) {
        Order order = orderReader.read(mealDelivery.getOrderId());
        mealDelivery.completeDelivery();
        orderManager.reduceRemainDeliveryCount(order);
        mealDeliveryRepository.update(mealDelivery);
    }

    private static List<FullDiningInfo> toMealDeliveryInfos(List<MealDelivery> mealDeliveries) {
        return mealDeliveries.stream()
                .map(
                        mealDelivery ->
                                FullDiningInfo.of(
                                        mealDelivery.getMealDeliveryId().getValue(),
                                        mealDelivery.hasFullDiningOption()))
                .toList();
    }
}
