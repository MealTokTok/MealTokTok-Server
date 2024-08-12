package core.startup.mealtoktok.api.order;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.order.dto.MealOrderRequest;
import core.startup.mealtoktok.api.order.dto.OrderDetailResponse;
import core.startup.mealtoktok.api.order.dto.OrderResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.OrderDetail;
import core.startup.mealtoktok.domain.order.OrderService;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.domain.order.TargetOrder;
import core.startup.mealtoktok.domain.user.User;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderApi implements OrderApiDocs {

    private final OrderService orderService;

    @PostMapping
    public Response<TargetOrder> orderMeals(
            @AuthenticationPrincipal User currentUser, @RequestBody MealOrderRequest request) {
        return Response.success(
                orderService.orderMeals(Orderer.from(currentUser), request.toContent()));
    }

    @GetMapping
    public Response<List<OrderResponse>> orderList(@AuthenticationPrincipal User currentUser) {
        List<Order> orderList = orderService.getOrderList(Orderer.from(currentUser));
        return Response.success(orderList.parallelStream().map(OrderResponse::from).toList());
    }

    @GetMapping("/{orderId}")
    public Response<OrderDetailResponse> orderDetail(
            @AuthenticationPrincipal User currentUser, @PathVariable Long orderId) {
        OrderDetail orderDetail =
                orderService.getOrderDetail(Orderer.from(currentUser), TargetOrder.from(orderId));
        return Response.success(OrderDetailResponse.from(orderDetail));
    }
}
