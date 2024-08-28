package core.startup.mealtoktok.api.mealdelivery;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import core.startup.mealtoktok.api.mealdelivery.dto.MealDeliveryResponse;
import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.common.dto.SliceResult;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliverySearchCond;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "도시락 배송 API")
public interface MealDeliveryApiDocs {

    @Operation(summary = "배송 중인 도시락 조회")
    Response<MealDeliveryResponse> deliveringMeal(User currentUser);

    @Operation(summary = "도시락 배송 목록 검색")
    Response<SliceResult<MealDeliveryResponse>> searchMealDeliveries(
            User currentUser, MealDeliverySearchCond cond, Cursor cursor);

    @Operation(summary = "도시락 배송 단건 조회")
    Response<MealDeliveryResponse> mealDelivery(Long mealDeliveryId);

    @Operation(summary = "상태별 도시락 배송 건수 조회")
    Response<Integer> countByDeliveryState(
            @AuthenticationPrincipal User currentUser, DeliveryState deliveryState);

    @Operation(summary = "최근 배송 완료된 도시락 조회")
    Response<MealDeliveryResponse> recentDeliveredMeal(User currentUser);

    @Operation(summary = "해당 주문의 다음 도시락 배송 조회")
    Response<MealDeliveryResponse> nextDeliveryMeal(String orderId);

    @Operation(summary = "도시락 배송 요청")
    Response<Void> requestDelivery(Long mealDeliveryId);

    @Operation(summary = "도시락 배송 시작")
    Response<Void> startDelivery(Long mealDeliveryId);

    @Operation(summary = "도시락 배송 완료")
    Response<Void> completeDelivery(Long mealDeliveryId);
}
