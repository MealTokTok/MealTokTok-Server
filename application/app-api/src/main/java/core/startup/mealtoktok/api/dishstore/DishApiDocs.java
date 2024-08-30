package core.startup.mealtoktok.api.dishstore;

import java.util.List;

import core.startup.mealtoktok.api.dishstore.request.DishRequest;
import core.startup.mealtoktok.api.dishstore.request.SearchDish;
import core.startup.mealtoktok.api.dishstore.response.DishResponse;
import core.startup.mealtoktok.api.global.dto.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "반찬 API")
public interface DishApiDocs {

    @Operation(summary = "반찬 생성")
    Response<Void> createDish(Long storeId, Long categoryId, List<MultipartFile> files, DishRequest request);

    @Operation(summary = "반찬 삭제")
    Response<Void> deleteDish(Long dishId);

    @Operation(summary = "반찬 수정")
    Response<Void> updateDish(Long dishId, List<MultipartFile> files, DishRequest request);

    @Operation(summary = "반찬 리스트 조회")
    Response<List<DishResponse>> readDishes(Long storeId, Long categoryId);

    @Operation(summary = "반찬 검색")
    Response<List<DishResponse>> searchDishes(Long storeId, SearchDish q);
}
