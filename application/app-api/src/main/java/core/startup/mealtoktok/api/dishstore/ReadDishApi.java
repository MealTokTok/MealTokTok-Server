package core.startup.mealtoktok.api.dishstore;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.dishstore.response.DishResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.dishstore.ReadDishService;
import core.startup.mealtoktok.domain.dishstore.TargetDishCategory;
import core.startup.mealtoktok.domain.dishstore.TargetDishStore;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReadDishApi implements ReadDishApiDocs {

    private final ReadDishService readDishService;

    @GetMapping("/stores/{storeId}/categories/{categoryId}/dishes")
    public Response<List<DishResponse>> readDishes(
            @PathVariable("storeId") Long storeId,
            @PathVariable("categoryId") Long categoryId,
            @AuthenticationPrincipal User currentUser) {

        List<DishResponse> dishResponses =
                readDishService
                        .readDishes(
                                TargetUser.from(currentUser.getUserId()),
                                TargetDishStore.from(storeId),
                                TargetDishCategory.from(categoryId))
                        .stream()
                        .map(DishResponse::from)
                        .toList();

        return Response.success(dishResponses);
    }
}
