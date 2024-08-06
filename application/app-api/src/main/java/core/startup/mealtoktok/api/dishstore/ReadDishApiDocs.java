package core.startup.mealtoktok.api.dishstore;

import java.util.List;

import core.startup.mealtoktok.api.dishstore.response.DishResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "반찬 API")
public interface ReadDishApiDocs {

    @Operation(summary = "반찬 리스트 조회")
    Response<List<DishResponse>> readDishes(Long storeId, Long categoryId, User currentUser);
}
