package core.startup.mealtoktok.api.meal.dto;

import java.util.List;

import core.startup.mealtoktok.api.dishstore.response.DishResponse;
import core.startup.mealtoktok.domain.meal.MealAndDishes;

public record MealDishResponse(Long mealId, String mealName, List<DishResponse> dishResponses) {

    public static MealDishResponse from(MealAndDishes mealAndDishes) {
        Long mealId = mealAndDishes.meal().mealId();
        String mealName = mealAndDishes.meal().mealInfo().mealName();
        List<DishResponse> dishResponses =
                mealAndDishes.dishes().stream().map(DishResponse::from).toList();
        return new MealDishResponse(mealId, mealName, dishResponses);
    }
}
