package core.startup.mealtoktok.api.order;

import core.startup.mealtoktok.api.order.dto.MealOrderRequest;
import core.startup.mealtoktok.api.order.dto.OrderCancelRequest;
import core.startup.mealtoktok.api.order.dto.OrderResponse;
import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.common.dto.SliceResult;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.order.OrderSearchCond;
import core.startup.mealtoktok.domain.order.OrderState;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "주문 API")
public interface OrderApiDocs {

    @Operation(summary = "도시락 주문")
    Response<OrderId> orderMeals(User currentUser, MealOrderRequest request);

    @Operation(summary = "주문 내역 검색")
    Response<SliceResult<OrderResponse>> searchOrders(
            User currentUser, OrderSearchCond cond, Cursor cursor);

    @Operation(summary = "주문 조회")
    Response<OrderResponse> getOrder(String orderId);

    @Operation(summary = "주문 접수")
    Response<OrderId> acceptOrder(String orderId);

    @Operation(summary = "주문 취소")
    Response<OrderId> cancelOrder(User currentUser, String orderId, OrderCancelRequest request);

    @Operation(summary = "주문 상태 조회")
    Response<OrderState> orderState(User currentUser, String orderId);

    @Operation(summary = "상태별 주문 건수 조회")
    Response<Integer> countOrders(User currentUser, OrderState orderState);
}
