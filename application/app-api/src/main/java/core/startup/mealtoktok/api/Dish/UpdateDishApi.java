package core.startup.mealtoktok.api.dish;

import core.startup.mealtoktok.api.dish.request.DishRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.dish.TargetDish;
import core.startup.mealtoktok.domain.dish.UpdateDishService;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UpdateDishApi {

    private final UpdateDishService updateDishService;

    @PatchMapping(("dishes/{dishId}"))
    public Response<Void> updateDish(@RequestParam("dishId") Long dishId,
                                     @RequestBody DishRequest request,
                                     @AuthenticationPrincipal User currentUser)
    {
        updateDishService.updateDish(TargetUser.from(currentUser.getUserId()), TargetDish.from(dishId), request.toDishInfo());
        return Response.success("반찬 수정 성공");
    }
}
