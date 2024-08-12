package core.startup.mealtoktok.api.dishstore;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import core.startup.mealtoktok.api.dishstore.request.DishStoreRequest;
import core.startup.mealtoktok.api.dishstore.response.DishStoreResponse;
import core.startup.mealtoktok.common.dto.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "반찬 가게 API")
public interface DishStoreApiDocs {

    @Operation(summary = "반찬 가게 생성")
    Response<Void> createDishStore(DishStoreRequest dishStoreRequest, User currentUser);

    @Operation(summary = "반찬 가게 삭제")
    Response<Void> deleteDishStore(Long dishStoreId, User currentUser);

    @Operation(summary = "반찬 가게 수정")
    Response<Void> updateDishStore(
            Long dishStoreId, DishStoreRequest dishStoreRequest, User currentUser);

    @Operation(summary = "반찬 가게 목록 조회")
    Response<List<DishStoreResponse>> readDishStores(User currentUser);

    @Operation(summary = "반찬 가게 상세 조회")
    Response<DishStoreResponse> readDishStores(Long dishStoreId, User currentUser);
}