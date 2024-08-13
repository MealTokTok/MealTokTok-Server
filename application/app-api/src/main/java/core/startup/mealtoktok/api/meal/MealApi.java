package core.startup.mealtoktok.api.meal;

import core.startup.mealtoktok.domain.meal.TargetMeal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.meal.dto.MealRequest;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.meal.MealService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MealApi {

    private final MealService mealService;

    @PostMapping("/meals")
    public Response<Void> createMeal(
            @RequestBody MealRequest request, @AuthenticationPrincipal User currentUser) {

        mealService.createMeal(request.toMealDishes());
        return Response.success("도시락 생성 성공");
    }

    @PutMapping("/meals/{mealId}")
    public Response<Void> updateMeal(
            @RequestBody MealRequest request,
            @PathVariable("mealId") Long mealId,
            @AuthenticationPrincipal User currentUser) {

        mealService.updateMeal(TargetMeal.from(mealId), request.toMealDishes());
        return Response.success("도시락 수정 성공");
    }
}
