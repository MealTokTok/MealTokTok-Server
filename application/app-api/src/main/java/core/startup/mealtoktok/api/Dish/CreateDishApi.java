package core.startup.mealtoktok.api.dish;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.dish.request.DishRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.dishstore.CreateDishService;
import core.startup.mealtoktok.domain.dishstore.TargetDishCategory;
import core.startup.mealtoktok.domain.dishstore.TargetDishStore;
import core.startup.mealtoktok.domain.user.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class CreateDishApi implements CreateDishApiDocs {

    private final CreateDishService createDishService;

    @PostMapping(("/stores/{storeId}/categories/{categoryId}/dishes"))
    public Response<Void> createDish(
            @PathVariable("storeId") Long storeId,
            @PathVariable("categoryId") Long categoryId,
            @RequestBody DishRequest request,
            @AuthenticationPrincipal User currentUser) {
        createDishService.createDish(
                TargetDishStore.from(storeId),
                TargetDishCategory.from(categoryId),
                request.toDishInfo());
        return Response.success("반찬 생성 성공");
    }
}
