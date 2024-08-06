package core.startup.mealtoktok.api.dishstore;

import core.startup.mealtoktok.api.dishstore.request.DishCategoryRequest;
import core.startup.mealtoktok.api.dishstore.response.DishCategoryResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "반찬 카테고리 API")
public interface DishCategoryApiDocs {

    @Operation(summary = "반찬 카테고리 생성")
    Response<Void> createDishCategory(DishCategoryRequest request, User currentUser);

    @Operation(summary = "반찬 카테고리 목록 조회")
    Response<List<DishCategoryResponse>> readDishCategories(User currentUser);

    @Operation(summary = "반찬 카테고리 삭제")
    Response<Void> deleteDishCategory(Long categoryId, User currentUser);

    @Operation(summary = "반찬 카테고리 수정")
    Response<Void> updateDishCategory(Long categoryId, DishCategoryRequest request, User currentUser);
}
