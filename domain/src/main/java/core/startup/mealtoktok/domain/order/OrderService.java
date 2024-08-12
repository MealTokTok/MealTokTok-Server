package core.startup.mealtoktok.domain.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.alarm.AlarmSender;
import core.startup.mealtoktok.domain.mealdelivery.FullDiningManager;
import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryReader;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryReserver;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderAppender orderAppender;
    private final OrderValidator orderValidator;
    private final OrderReader orderReader;
    private final MealDeliveryReserver mealDeliveryReserver;
    private final MealDeliveryReader mealDeliveryReader;
    private final FullDiningManager fullDiningManager;
    private final AlarmSender alarmSender;

    @Transactional
    public TargetOrder orderMeals(Orderer orderer, OrderContent orderContent) {
        orderValidator.validate(orderContent);
        TargetOrder targetOrder = orderAppender.append(orderer, orderContent);
        List<MealDelivery> mealDeliveries =
                mealDeliveryReserver.reserve(targetOrder, orderContent.orderedMeals());
        fullDiningManager.reserve(mealDeliveries);
        return targetOrder;
    }

    public List<Order> getOrderList(Orderer orderer) {
        return orderReader.read(orderer);
    }

    public OrderDetail getOrderDetail(Orderer orderer, TargetOrder targetOrder) {
        Order order = orderReader.read(targetOrder);
        orderValidator.validate(order, orderer);
        List<MealDelivery> mealDeliveries = mealDeliveryReader.read(TargetOrder.from(order));
        return OrderDetail.of(order, mealDeliveries);
    }
}
