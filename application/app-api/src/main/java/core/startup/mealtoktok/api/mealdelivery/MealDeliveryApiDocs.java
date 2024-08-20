package core.startup.mealtoktok.api.mealdelivery;

import java.util.List;

import core.startup.mealtoktok.api.mealdelivery.dto.FullDiningResponse;
import core.startup.mealtoktok.api.mealdelivery.dto.MealDeliveryResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.mealdelivery.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "도시락 배달 API")
public interface MealDeliveryApiDocs {

    @Operation(summary = "배송 중인 도시락 조회")
    Response<MealDeliveryResponse> deliveringMeal(User currentUser);

    @Operation(summary = "도시락 배송 단건 조회")
    Response<MealDeliveryResponse> mealDelivery(Long mealDeliveryId);

    @Operation(summary = "최근 배송 완료된 도시락 조회")
    Response<MealDeliveryResponse> recentDeliveredMeal(User currentUser);

    @Operation(summary = "도시락 배송 상태 변경")
    Response<Void> changeDeliveryState(Long mealDeliveryId, DeliveryState deliveryState);

    @Operation(summary = "풀대접 서비스 수거 상태 변경")
    Response<Void> changeCollectingState(Long fullDiningId, CollectingState collectingState);

    @Operation(summary = "풀대접 서비스 수거 요청된 다회용기 갯수 조회")
    Response<Integer> countCollectRequestContainers(User currentUser);

    @Operation(summary = "풀대접 서비스 다회용기 납부 목록 조회")
    Response<List<FullDiningResponse>> fullDinings(User currentUser);
}