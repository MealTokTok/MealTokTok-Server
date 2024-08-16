package core.startup.mealtoktok.api.meal;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import core.startup.mealtoktok.api.meal.dto.MealDishResponse;
import core.startup.mealtoktok.api.meal.dto.MealRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.user.User;

import io.swagger.v3.oas.annotations.Operation;
@Tag(name = "도시락 API")
public interface MealApiDocs {

    @Operation(summary = "도시락 생성")
    Response<Void> createMeal(MealRequest request, User currentUser);

    @Operation(summary = "도시락 조회")
    Response<MealDishResponse> readMeal(Long mealId, User currentUser);

    @Operation(summary = "도시락 리스트 조회")
    Response<List<MealDishResponse>> readMeals(User currentUser);

    @Operation(summary = "도시락 삭제")
    Response<Void> deleteMeal(Long mealId, User currentUser);

    @Operation(summary = "도시락 수정")
    Response<Void> updateMeal(MealRequest request, Long mealId, User currentUser);
}
