package core.startup.mealtoktok.api.order;

import java.util.List;

import core.startup.mealtoktok.api.order.dto.MealOrderRequest;
import core.startup.mealtoktok.api.order.dto.OrderDetailResponse;
import core.startup.mealtoktok.api.order.dto.OrderResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.order.TargetOrder;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "주문 API")
public interface OrderApiDocs {

    @Operation(summary = "도시락 주문")
    Response<TargetOrder> orderMeals(User currentUser, MealOrderRequest request);

    @Operation(summary = "주문 내역 전체 조회")
    Response<List<OrderResponse>> orderList(User currentUser);

    @Operation(summary = "주문 내역 상세 조회")
    Response<OrderDetailResponse> orderDetail(User currentUser, Long orderId);
}
