package core.startup.mealtoktok.api.dishstore.response;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.dishstore.Dish;
import core.startup.mealtoktok.domain.dishstore.DishState;

public record DishResponse(
        Long dishId,
        String dishName,
        Money dishPrice,
        String imgUrl,
        int dishQuantity,
        DishState dishState) {

    public static DishResponse from(Dish dish) {
        return new DishResponse(
                dish.getDishId(),
                dish.getDishName(),
                dish.getDishPrice(),
                dish.getDishImage().getImageUrl(),
                dish.getDishQuantity(),
                dish.getDishState());
    }
}
