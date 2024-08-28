package core.startup.mealtoktok.domain.mealdelivery;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.FullDiningInfo;
import core.startup.mealtoktok.domain.order.MealDeliveryReservationInfo;
import core.startup.mealtoktok.domain.order.MealDeliveryReserver;

@Component
@RequiredArgsConstructor
public class MealDeliveryManager implements MealDeliveryReserver {

    private final MealDeliveryRepository mealDeliveryRepository;

    @Override
    public List<FullDiningInfo> reserve(List<MealDeliveryReservationInfo> reservationInfos) {
        List<MealDelivery> mealDeliveries =
                reservationInfos.stream().map(MealDelivery::create).toList();

        mealDeliveryRepository.saveAll(mealDeliveries);
        return toMealDeliveryInfos(mealDeliveries);
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
