package core.startup.mealtoktok.api.meal.dto;

import java.util.List;

import core.startup.mealtoktok.api.dishstore.response.DishResponse;
import core.startup.mealtoktok.domain.meal.MealAndDishes;

public record MealDishResponse(MealResponse meal, List<DishResponse> dishes) {

    public static MealDishResponse from(MealAndDishes mealAndDishes) {
        MealResponse mealResponse = MealResponse.from(mealAndDishes.meal());
        List<DishResponse> dishResponses =
                mealAndDishes.dishes().stream()
                        .map(DishResponse::from)
                        .toList();
        return new MealDishResponse(mealResponse, dishResponses);
    }
}
