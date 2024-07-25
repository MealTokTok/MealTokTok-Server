package core.startup.mealtoktok.api.dish;

import core.startup.mealtoktok.api.dish.request.CreateDishRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.DishStore.TargetDishStore;
import core.startup.mealtoktok.domain.dish.CreateDishService;
import core.startup.mealtoktok.domain.dishCategory.TargetDishCategory;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CreateDishApi {

    private final CreateDishService createDishService;

    @PostMapping(("/stores/{storeId}/categories/{categoryId}/dishes"))
    public Response<Void> createDish(@RequestParam("storeId") Long storeId,
                                     @RequestParam("categoryId") Long categoryId,
                                     @RequestBody CreateDishRequest request,
                                     @AuthenticationPrincipal User currentUser)
    {
        createDishService.createDish(TargetUser.from(currentUser), TargetDishStore.from(storeId), TargetDishCategory.from(categoryId), request.toDishInfo());
        return Response.success("반찬 생성 성공");
    }

}
