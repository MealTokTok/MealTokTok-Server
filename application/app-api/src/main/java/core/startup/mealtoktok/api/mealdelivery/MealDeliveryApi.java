package core.startup.mealtoktok.api.mealdelivery;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.mealdelivery.dto.FullDiningResponse;
import core.startup.mealtoktok.api.mealdelivery.dto.MealDeliveryResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.mealdelivery.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.FullDining;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryService;
import core.startup.mealtoktok.domain.mealdelivery.TargetFullDining;
import core.startup.mealtoktok.domain.mealdelivery.TargetMealDelivery;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.domain.user.User;

@RequestMapping("/api/v1/meal-deliveries")
@RestController
@RequiredArgsConstructor
public class MealDeliveryApi implements MealDeliveryApiDocs {

    private final MealDeliveryService mealDeliveryService;

    @GetMapping("/delivering")
    public Response<MealDeliveryResponse> deliveringMeal(
            @AuthenticationPrincipal User currentUser) {
        return Response.success(
                MealDeliveryResponse.from(
                        mealDeliveryService.getDeliveringMeal(Orderer.from(currentUser))));
    }

    @GetMapping("/{mealDeliveryId}")
    public Response<MealDeliveryResponse> mealDelivery(@PathVariable Long mealDeliveryId) {
        return Response.success(
                MealDeliveryResponse.from(
                        mealDeliveryService.getMealDelivery(
                                TargetMealDelivery.from(mealDeliveryId))));
    }

    @GetMapping("/delivered")
    public Response<MealDeliveryResponse> recentDeliveredMeal(
            @AuthenticationPrincipal User currentUser) {
        return Response.success(
                MealDeliveryResponse.from(
                        mealDeliveryService.getRecentDeliveredMeal(Orderer.from(currentUser))));
    }

    @PatchMapping("/{mealDeliveryId}/{deliveryState}")
    public Response<Void> changeDeliveryState(
            @PathVariable Long mealDeliveryId, @PathVariable DeliveryState deliveryState) {
        mealDeliveryService.changeDeliveryState(
                TargetMealDelivery.from(mealDeliveryId), deliveryState);
        return Response.success("배송 상태 변경 성공");
    }

    @PatchMapping("/full-dinings/{fullDiningId}/{collectingState}")
    public Response<Void> changeCollectingState(
            @PathVariable Long fullDiningId, @PathVariable CollectingState collectingState) {
        mealDeliveryService.changeCollectingState(
                TargetFullDining.from(fullDiningId), collectingState);
        return Response.success("수거 상태 변경 성공");
    }

    @GetMapping("/full-dinings/collect-requested/count")
    public Response<Integer> countCollectRequestContainers(
            @AuthenticationPrincipal User currentUser) {
        return Response.success(
                mealDeliveryService.countCollectRequestContainers(Orderer.from(currentUser)));
    }

    @GetMapping("/full-dinings")
    public Response<List<FullDiningResponse>> fullDinings(
            @AuthenticationPrincipal User currentUser) {
        List<FullDining> fullDinings =
                mealDeliveryService.getFullDinings(Orderer.from(currentUser));
        return Response.success(
                fullDinings.parallelStream().map(FullDiningResponse::from).toList());
    }
}
