package core.startup.mealtoktok.api.dishstore.response;

import core.startup.mealtoktok.domain.dishstore.Dish;
import core.startup.mealtoktok.domain.order.Money;

public record DishResponse(Long dishId, String dishName, Money dishPrice, String imgUrl) {
    public static DishResponse from(Dish dish) {
        return new DishResponse(
                dish.getDishId(), dish.getDishName(), dish.getDishPrice(), dish.getImgUrl());
    }
}
