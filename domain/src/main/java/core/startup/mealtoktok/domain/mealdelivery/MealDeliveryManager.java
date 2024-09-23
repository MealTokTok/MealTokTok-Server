package core.startup.mealtoktok.domain.mealdelivery;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

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
    private final FullDiningReserver fullDiningReserver;

    public void reserve(List<MealDeliveryReservationInfo> reservationInfos) {
        List<MealDelivery> mealDeliveries =
                reservationInfos.stream().map(MealDelivery::create).toList();

        List<MealDelivery> reservedDeliveries = mealDeliveryRepository.saveAll(mealDeliveries);
        fullDiningReserver.reserve(reservedDeliveries);
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
}
