package core.startup.mealtoktok.domain.order;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.VALID_DATE_TIME;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;
import core.startup.mealtoktok.domain.mealdelivery.FullDiningManager;
import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryReader;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryReserver;
import core.startup.mealtoktok.domain.user.DeliveryAddress;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderAppender orderAppender;
    private final OrderValidator orderValidator;
    private final OrderReader orderReader;
    private final MealDeliveryReserver mealDeliveryReserver;
    private final MealDeliveryReader mealDeliveryReader;
    private final FullDiningManager fullDiningManager;

    @Transactional
    public TargetOrder orderMeals(
            Orderer orderer, OrderContent orderContent, DeliveryAddress deliveryAddress) {
        orderValidator.validate(orderContent);
        TargetOrder targetOrder = orderAppender.append(orderer, orderContent, deliveryAddress);
        List<MealDelivery> mealDeliveries =
                mealDeliveryReserver.reserve(orderContent.toMealDeliveries(targetOrder));
        fullDiningManager.reserve(mealDeliveries);
        return targetOrder;
    }

    public SliceResult<Order> searchOrders(Orderer orderer, OrderSearchCond cond, Cursor cursor) {
        return orderReader.read(orderer, cond, cursor);
    }

    public OrderDetail getOrderDetail(Orderer orderer, TargetOrder targetOrder) {
        Order order = orderReader.read(targetOrder);
        orderValidator.validate(order, orderer);
        List<MealDelivery> mealDeliveries = mealDeliveryReader.read(order.getOrderId());
        return OrderDetail.of(order, mealDeliveries);
    }

    public OrderState getOrderState(Orderer orderer, TargetOrder targetOrder) {
        Order order = orderReader.read(targetOrder);
        orderValidator.validate(order, orderer);
        return order.getOrderState();
    }

    public Integer countByOrderState(Orderer orderer, OrderState orderState) {
        return orderReader.count(orderer, orderState, VALID_DATE_TIME, LocalDateTime.now());
    }
}
