package core.startup.mealtoktok.api.dishstore;

import static core.startup.mealtoktok.api.global.util.FileMapper.toFile;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.dishstore.request.DishRequest;
import core.startup.mealtoktok.api.dishstore.request.SearchDish;
import core.startup.mealtoktok.api.dishstore.response.DishResponse;
import core.startup.mealtoktok.api.global.dto.Response;
import core.startup.mealtoktok.domain.dishstore.DishService;
import core.startup.mealtoktok.domain.dishstore.TargetDish;
import core.startup.mealtoktok.domain.dishstore.TargetDishCategory;
import core.startup.mealtoktok.domain.dishstore.TargetDishStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DishApi implements DishApiDocs {

    private final DishService dishService;

    @PostMapping(
            value = "/admin/stores/{storeId}/categories/{categoryId}/dishes",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<Void> createDish(
            @PathVariable("storeId") Long storeId,
            @PathVariable("categoryId") Long categoryId,
            @RequestPart MultipartFile file,
            @RequestPart("request") DishRequest request) {
        dishService.createDish(
                TargetDishStore.from(storeId),
                TargetDishCategory.from(categoryId),
                request.toContent(),
                toFile(file));
        return Response.success("반찬 생성 성공");
    }

    @DeleteMapping(("/admin/dishes/{dishId}"))
    public Response<Void> deleteDish(@PathVariable("dishId") Long dishId) {
        dishService.deleteDish(TargetDish.from(dishId));
        return Response.success("반찬 삭제 성공");
    }

    @GetMapping("/stores/{storeId}/categories/{categoryId}/dishes")
    public Response<List<DishResponse>> readDishes(@PathVariable("categoryId") Long categoryId) {

        List<DishResponse> dishResponses =
                dishService.readDishes(TargetDishCategory.from(categoryId)).stream()
                        .map(DishResponse::from)
                        .toList();

        return Response.success(dishResponses);
    }

    @PutMapping(value = "/admin/dishes/{dishId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<Void> updateDish(
            @PathVariable("dishId") Long dishId,
            @RequestPart(required = false) MultipartFile file,
            @RequestPart("request") DishRequest request) {
        dishService.updateDish(TargetDish.from(dishId), toFile(file), request.toContent());
        return Response.success("반찬 수정 성공");
    }

    @GetMapping("/stores/{storeId}/dishes/search")
    public Response<List<DishResponse>> searchDishes(@ModelAttribute("q") SearchDish q) {
        List<DishResponse> dishResponses =
                dishService.searchDishes(q.keyword()).stream().map(DishResponse::from).toList();
        return Response.success(dishResponses);
    }
}
