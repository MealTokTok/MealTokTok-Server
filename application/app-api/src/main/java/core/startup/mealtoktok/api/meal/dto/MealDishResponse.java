package core.startup.mealtoktok.api.meal.dto;

import java.util.List;

import core.startup.mealtoktok.api.dishstore.response.DishResponse;
import core.startup.mealtoktok.domain.meal.MealWithDishes;

public record MealDishResponse(MealResponse meal, List<DishResponse> dishes) {

    public static MealDishResponse from(MealWithDishes mealWithDishes) {
        MealResponse mealResponse = MealResponse.from(mealWithDishes.meal());
        List<DishResponse> dishResponses =
                mealWithDishes.dishes().stream().map(DishResponse::from).toList();
        return new MealDishResponse(mealResponse, dishResponses);
    }
}
