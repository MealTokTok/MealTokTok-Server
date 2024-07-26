package core.startup.mealtoktok.api.dish;

import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.dishstore.DeleteDishService;
import core.startup.mealtoktok.domain.dishstore.TargetDish;
import core.startup.mealtoktok.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class DeleteDishApi implements DeleteDishApiDocs{

    private final DeleteDishService deleteDishService;

    @DeleteMapping(("dishes/{dishId}"))
    public Response<Void> deleteDish(@PathVariable("dishId") Long dishId,
                                     @AuthenticationPrincipal User currentUser)
    {
        deleteDishService.deleteDish(TargetDish.from(dishId));
        return Response.success("반찬 삭제 성공");
    }
}
