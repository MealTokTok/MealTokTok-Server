package core.startup.mealtoktok.api.dish;

import core.startup.mealtoktok.api.dish.response.DishResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.dish.TargetDishStore;
import core.startup.mealtoktok.domain.dish.ReadDishService;
import core.startup.mealtoktok.domain.dish.TargetDishCategory;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReadDishApi implements ReadDishApiDocs{

    private final ReadDishService readDishService;

    @GetMapping("/stores/{storeId}/categories/{categoryId}/dishes")
    public Response<List<DishResponse>> readDishes(@PathVariable("storeId") Long storeId,
                                                   @PathVariable("categoryId") Long categoryId,
                                                   @AuthenticationPrincipal User currentUser) {

        List<DishResponse> dishResponses = readDishService.readDishes(
                        TargetUser.from(currentUser.getUserId()),
                        TargetDishStore.from(storeId),
                        TargetDishCategory.from(categoryId))
                .stream().map(DishResponse::from).toList();

        return Response.success(dishResponses);
    }
}
