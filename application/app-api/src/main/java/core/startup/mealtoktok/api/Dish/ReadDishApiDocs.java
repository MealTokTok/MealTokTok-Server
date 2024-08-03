package core.startup.mealtoktok.api.dish;

import java.util.List;

import core.startup.mealtoktok.api.dish.response.DishResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "반찬 API")
public interface ReadDishApiDocs {

    @Operation(summary = "반찬 리스트 조회")
    Response<List<DishResponse>> readDishes(Long storeId, Long categoryId, User currentUser);
}
