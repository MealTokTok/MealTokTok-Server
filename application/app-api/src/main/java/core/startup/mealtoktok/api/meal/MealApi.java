package core.startup.mealtoktok.api.meal;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.meal.dto.MealDishResponse;
import core.startup.mealtoktok.api.meal.dto.MealRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.meal.MealAndDishes;
import core.startup.mealtoktok.domain.meal.MealOwner;
import core.startup.mealtoktok.domain.meal.MealService;
import core.startup.mealtoktok.domain.meal.TargetMeal;
import core.startup.mealtoktok.domain.user.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MealApi implements MealApiDocs {

    private final MealService mealService;

    @PostMapping("/meals")
    public Response<Void> createMeal(
            @RequestBody MealRequest request, @AuthenticationPrincipal User currentUser) {
        mealService.createMeal(MealOwner.from(currentUser), request.toContent());
        return Response.success("도시락 생성 성공");
    }

    @GetMapping("/meals/{mealId}")
    public Response<MealDishResponse> readMeal(@PathVariable("mealId") Long mealId) {
        MealAndDishes mealAndDishes = mealService.readMealAndDishes(TargetMeal.from(mealId));
        return Response.success(MealDishResponse.from(mealAndDishes));
    }

    @GetMapping("/meals")
    public Response<List<MealDishResponse>> readMeals(@AuthenticationPrincipal User currentUser) {
        List<MealAndDishes> mealAndDishesList =
                mealService.readMealAndDishes(MealOwner.from(currentUser));
        List<MealDishResponse> mealDishResponses =
                mealAndDishesList.stream().map(MealDishResponse::from).toList();
        return Response.success(mealDishResponses);
    }

    @DeleteMapping("/meals/{mealId}")
    public Response<Void> deleteMeal(
            @PathVariable("mealId") Long mealId, @AuthenticationPrincipal User currentUser) {
        mealService.deleteMeal(MealOwner.from(currentUser), TargetMeal.from(mealId));
        return Response.success("도시락 삭제 성공");
    }

    @PutMapping("/meals/{mealId}")
    public Response<Void> updateMeal(
            @RequestBody MealRequest request,
            @PathVariable("mealId") Long mealId,
            @AuthenticationPrincipal User currentUser) {
        mealService.updateMeal(
                MealOwner.from(currentUser), TargetMeal.from(mealId), request.toContent());
        return Response.success("도시락 수정 성공");
    }
}
