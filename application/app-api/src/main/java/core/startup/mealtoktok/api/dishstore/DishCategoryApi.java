package core.startup.mealtoktok.api.dishstore;

import core.startup.mealtoktok.api.dishstore.request.DishCategoryRequest;
import core.startup.mealtoktok.api.dishstore.response.DishCategoryResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.dishstore.DishCategoryService;
import core.startup.mealtoktok.domain.dishstore.TargetDishCategory;
import core.startup.mealtoktok.domain.user.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DishCategoryApi implements DishCategoryApiDocs{

    private final DishCategoryService dishCategoryService;

    @PostMapping("/admin/dish-categories")
    public Response<Void> createDishCategory(
            @RequestBody DishCategoryRequest request,
            @AuthenticationPrincipal User currentUser) {
        dishCategoryService.createDishCategory(request.toDishCategoryInfo());
        return Response.success("반찬 카테고리 생성 성공");
    }

    @GetMapping("/dish-categories")
    public Response<List<DishCategoryResponse>> readDishCategories(
            @AuthenticationPrincipal User currnetUser) {
        List<DishCategoryResponse> dishCategoryResponses = dishCategoryService.readDishCategories()
                .stream()
                .map(DishCategoryResponse::from)
                .toList();
        return Response.success(dishCategoryResponses);
    }

    @DeleteMapping("/admin/dish-categories/{categoryId}")
    public Response<Void> deleteDishCategory(
            @PathVariable("categoryId") Long categoryId,
            @AuthenticationPrincipal User currentUser) {
        dishCategoryService.deleteDishCategory(TargetDishCategory.from(categoryId));
        return Response.success("반찬 카테고리 삭제 성공");
    }

    @PatchMapping("/admin/dish-categories/{categoryId}")
    public Response<Void> updateDishCategory(
            @PathVariable("categoryId") Long categoryId,
            @RequestBody DishCategoryRequest request,
            @AuthenticationPrincipal User currentUser) {
        dishCategoryService.updateDishCategory(TargetDishCategory.from(categoryId),
                request.toDishCategoryInfo());
        return Response.success("반찬 카테고리 수정 성공");
    }
}