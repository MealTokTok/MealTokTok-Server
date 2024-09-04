package core.startup.mealtoktok.domain.order;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.VALID_DATE_TIME;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;
import core.startup.mealtoktok.domain.user.DeliveryAddress;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderAppender orderAppender;
    private final OrderValidator orderValidator;
    private final OrderReader orderReader;
    private final MealDeliveryReserver mealDeliveryReserver;
    private final OrderManager orderManager;
    private final PaymentCanceler paymentCanceler;

    @Transactional
    public OrderId orderMeals(
            Orderer orderer, OrderContent orderContent, DeliveryAddress deliveryAddress) {
        orderValidator.validate(orderContent);
        OrderId orderId = orderAppender.append(orderer, orderContent, deliveryAddress);
        mealDeliveryReserver.reserve(orderContent.toReservationInfos(orderId));
        return orderId;
    }

    public OrderId acceptOrder(OrderId orderId) {
        Order order = orderReader.read(orderId);
        orderManager.acceptOrder(order);
        return orderId;
    }

    @Transactional
    public OrderId cancelOrder(Orderer orderer, OrderId orderId, String cancelReason) {
        Order order = orderReader.read(orderId);
        orderValidator.validate(order, orderer);
        orderManager.cancelOrder(order);
        paymentCanceler.cancel(order, cancelReason);
        return orderId;
    }

    public SliceResult<Order> searchOrders(Orderer orderer, OrderSearchCond cond, Cursor cursor) {
        return orderReader.read(orderer, cond, cursor);
    }

    public Order getOrder(OrderId orderId) {
        return orderReader.read(orderId);
    }

    public OrderState getOrderState(Orderer orderer, OrderId orderId) {
        Order order = orderReader.read(orderId);
        orderValidator.validate(order, orderer);
        return order.getOrderState();
    }

    public Integer countByOrderState(Orderer orderer, OrderState orderState) {
        return orderReader.count(orderer, orderState, VALID_DATE_TIME, LocalDateTime.now());
    }
}
