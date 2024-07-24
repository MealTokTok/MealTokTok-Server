package core.startup.mealtoktok.api.Dish;

import core.startup.mealtoktok.api.Dish.request.CreateDishRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.DishStore.TargetDishStore;
import core.startup.mealtoktok.domain.dish.CreateDishService;
import core.startup.mealtoktok.domain.dishCategory.TargetDishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CreateDishApi {

    private final CreateDishService createDishService;

    @PostMapping(("/{storeId}/{categoryId}/dishes"))
    public Response<Void> createDish(@RequestParam Long storeId,
                                     @RequestParam Long categoryId,
                                     @RequestBody CreateDishRequest request)
    {
        createDishService.createDish(TargetDishStore.from(storeId), TargetDishCategory.from(categoryId), request.toDishInfo());
        return Response.success("반찬 생성 성공");
    }

}
