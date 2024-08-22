package core.startup.mealtoktok.api.dishstore;

import java.util.List;

import core.startup.mealtoktok.api.dishstore.request.DishCategoryRequest;
import core.startup.mealtoktok.api.dishstore.response.DishCategoryResponse;
import core.startup.mealtoktok.common.dto.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "반찬 카테고리 API")
public interface DishCategoryApiDocs {

    @Operation(summary = "반찬 카테고리 생성")
    Response<Void> createDishCategory(DishCategoryRequest request);

    @Operation(summary = "반찬 카테고리 목록 조회")
    Response<List<DishCategoryResponse>> readDishCategories();

    @Operation(summary = "반찬 카테고리 삭제")
    Response<Void> deleteDishCategory(Long categoryId);

    @Operation(summary = "반찬 카테고리 수정")
    Response<Void> updateDishCategory(Long categoryId, DishCategoryRequest request);
}
