package core.startup.mealtoktok.api.order.dto;

import java.time.LocalDateTime;

import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.OrderPrice;
import core.startup.mealtoktok.domain.order.OrderState;
import core.startup.mealtoktok.domain.order.OrderType;
import core.startup.mealtoktok.domain.order.Orderer;

public record OrderResponse(
        String orderId,
        OrderType orderType,
        OrderState orderState,
        String specialInstruction,
        Orderer orderer,
        OrderPrice orderPrice,
        Integer totalMealDeliveryCount,
        Integer remainingMealDeliveryCount,
        LocalDateTime orderTime) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getOrderId().toString(),
                order.getOrderType(),
                order.getOrderState(),
                order.getSpecialInstruction(),
                order.getOrderer(),
                order.getOrderPrice(),
                order.getTotalMealDeliveryCount(),
                order.getRemainingMealDeliveryCount(),
                order.getOrderTime());
    }
}
