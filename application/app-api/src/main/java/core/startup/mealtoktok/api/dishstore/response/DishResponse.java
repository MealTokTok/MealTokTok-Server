package core.startup.mealtoktok.api.dishstore.response;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.dishstore.Dish;

public record DishResponse(
        Long dishId, String dishName, Money dishPrice, String imgUrl, boolean isSoldOut) {

    public static DishResponse from(Dish dish) {
        return new DishResponse(
                dish.getDishId(),
                dish.getDishName(),
                dish.getDishPrice(),
                dish.getImgUrl(),
                dish.isSoldOut());
    }
}
