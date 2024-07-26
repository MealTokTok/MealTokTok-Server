package core.startup.mealtoktok.api.dish;

import core.startup.mealtoktok.api.dish.request.DishRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.dishstore.TargetDish;
import core.startup.mealtoktok.domain.dishstore.UpdateDishService;
import core.startup.mealtoktok.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UpdateDishApi implements UpdateDishApiDocs{

    private final UpdateDishService updateDishService;

    @PatchMapping(("/admin/dishes/{dishId}"))
    public Response<Void> updateDish(@PathVariable("dishId") Long dishId,
                                     @RequestBody DishRequest request,
                                     @AuthenticationPrincipal User currentUser)
    {
        updateDishService.updateDish(TargetDish.from(dishId), request.toDishInfo());
        return Response.success("반찬 수정 성공");
    }
}
