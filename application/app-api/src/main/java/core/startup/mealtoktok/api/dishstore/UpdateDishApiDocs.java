package core.startup.mealtoktok.api.dishstore;

import core.startup.mealtoktok.api.dishstore.request.DishRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "반찬 API")
public interface UpdateDishApiDocs {

    @Operation(summary = "반찬 수정")
    Response<Void> updateDish(Long dishId, DishRequest request, User currentUser);
}
