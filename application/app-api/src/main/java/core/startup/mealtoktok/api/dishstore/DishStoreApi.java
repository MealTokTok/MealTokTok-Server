package core.startup.mealtoktok.api.dishstore;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.dishstore.request.DishStoreRequest;
import core.startup.mealtoktok.api.dishstore.response.DishStoreResponse;
import core.startup.mealtoktok.api.global.dto.Response;
import core.startup.mealtoktok.domain.dishstore.DishStore;
import core.startup.mealtoktok.domain.dishstore.DishStoreService;
import core.startup.mealtoktok.domain.dishstore.TargetDishStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DishStoreApi implements DishStoreApiDocs {

    private final DishStoreService dishStoreService;

    @PostMapping("/admin/dish-stores")
    public Response<Void> createDishStore(@RequestBody DishStoreRequest dishStoreRequest) {
        dishStoreService.createDishStore(dishStoreRequest.toDishStoreInfo());
        return Response.success("반찬 가게 생성 성공");
    }

    @DeleteMapping("/admin/dish-stores/{dishStoreId}")
    public Response<Void> deleteDishStore(@PathVariable("dishStoreId") Long dishStoreId) {
        dishStoreService.deleteDishStore(TargetDishStore.from(dishStoreId));
        return Response.success("반찬 가게 삭제 성공");
    }

    @PutMapping("/admin/dish-stores/{dishStoreId}")
    public Response<Void> updateDishStore(
            @PathVariable("dishStoreId") Long dishStoreId,
            @RequestBody DishStoreRequest dishStoreRequest) {
        dishStoreService.updateDishStore(
                TargetDishStore.from(dishStoreId), dishStoreRequest.toDishStoreInfo());
        return Response.success("반찬 가게 수정 성공");
    }

    @GetMapping("/dish-stores")
    public Response<List<DishStoreResponse>> readDishStores() {
        List<DishStoreResponse> dishStoreResponse =
                dishStoreService.readDishStores().stream().map(DishStoreResponse::from).toList();
        return Response.success(dishStoreResponse);
    }

    @GetMapping("/dish-stores/{dishStoreId}")
    public Response<DishStoreResponse> readDishStores(
            @PathVariable("dishStoreId") Long dishStoreId) {
        DishStore dishStore = dishStoreService.readDishStore(TargetDishStore.from(dishStoreId));
        return Response.success(DishStoreResponse.from(dishStore));
    }
}
