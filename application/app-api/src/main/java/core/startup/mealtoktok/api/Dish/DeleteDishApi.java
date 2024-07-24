package core.startup.mealtoktok.api.Dish;

import core.startup.mealtoktok.api.Dish.request.CreateDishRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.DishStore.TargetDishStore;
import core.startup.mealtoktok.domain.dish.DeleteDishService;
import core.startup.mealtoktok.domain.dish.TargetDish;
import core.startup.mealtoktok.domain.dishCategory.TargetDishCategory;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DeleteDishApi {

    private final DeleteDishService deleteDishService;

    @DeleteMapping(("dishes/{dishId}"))
    public Response<Void> deleteDish(@RequestParam Long dishId)
                                     //@AuthenticationPrincipal User currentUser)
    {
        deleteDishService.deleteDish(TargetDish.from(dishId));
        return Response.success("반찬 삭제 성공");
    }
}
