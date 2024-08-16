package core.startup.mealtoktok.api.dishstore;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.dishstore.request.DishRequest;
import core.startup.mealtoktok.api.dishstore.request.SearchDish;
import core.startup.mealtoktok.api.dishstore.response.DishResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.dishstore.DishService;
import core.startup.mealtoktok.domain.dishstore.TargetDish;
import core.startup.mealtoktok.domain.dishstore.TargetDishCategory;
import core.startup.mealtoktok.domain.dishstore.TargetDishStore;
import core.startup.mealtoktok.domain.user.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DishApi implements DishApiDocs {

    private final DishService dishService;

    @PostMapping(("/admin/stores/{storeId}/categories/{categoryId}/dishes"))
    public Response<Void> createDish(
            @PathVariable("storeId") Long storeId,
            @PathVariable("categoryId") Long categoryId,
            @RequestBody DishRequest request,
            @AuthenticationPrincipal User currentUser) {
        dishService.createDish(
                TargetDishStore.from(storeId),
                TargetDishCategory.from(categoryId),
                request.toDishInfo());
        return Response.success("반찬 생성 성공");
    }

    @DeleteMapping(("/admin/dishes/{dishId}"))
    public Response<Void> deleteDish(
            @PathVariable("dishId") Long dishId, @AuthenticationPrincipal User currentUser) {
        dishService.deleteDish(TargetDish.from(dishId));
        return Response.success("반찬 삭제 성공");
    }

    @GetMapping("/stores/{storeId}/categories/{categoryId}/dishes")
    public Response<List<DishResponse>> readDishes(
            @PathVariable("storeId") Long storeId,
            @PathVariable("categoryId") Long categoryId,
            @AuthenticationPrincipal User currentUser) {

        List<DishResponse> dishResponses =
                dishService
                        .readDishes(
                                TargetDishStore.from(storeId), TargetDishCategory.from(categoryId))
                        .stream()
                        .map(DishResponse::from)
                        .toList();

        return Response.success(dishResponses);
    }

    @PatchMapping(("/admin/dishes/{dishId}"))
    public Response<Void> updateDish(
            @PathVariable("dishId") Long dishId,
            @RequestBody DishRequest request,
            @AuthenticationPrincipal User currentUser) {
        dishService.updateDish(TargetDish.from(dishId), request.toDishInfo());
        return Response.success("반찬 수정 성공");
    }

    @GetMapping("/stores/{storeId}/dishes/search")
    public Response<List<DishResponse>> searchDishes(
            @PathVariable("storeId") Long storeId,
            @ModelAttribute("q") SearchDish q,
            @AuthenticationPrincipal User currentUser) {
        List<DishResponse> dishResponses =
                dishService.searchDishes(TargetDishStore.from(storeId), q.keyword()).stream()
                        .map(DishResponse::from)
                        .toList();
        return Response.success(dishResponses);
    }
}
