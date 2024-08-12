package core.startup.mealtoktok.api.order.dto;

import java.util.List;

import core.startup.mealtoktok.api.mealdelivery.dto.MealDeliveryResponse;
import core.startup.mealtoktok.domain.order.OrderDetail;

public record OrderDetailResponse(OrderResponse order, List<MealDeliveryResponse> mealDeliveries) {

    public static OrderDetailResponse from(OrderDetail orderDetail) {
        return new OrderDetailResponse(
                OrderResponse.from(orderDetail.order()),
                orderDetail.mealDeliveries().parallelStream()
                        .map(MealDeliveryResponse::from)
                        .toList());
    }
}
