package core.startup.mealtoktok.api.dishstore.response;

import core.startup.mealtoktok.domain.dishstore.Dish;

public record DishResponse(Long dishId, String dishName, String imgUrl) {
    public static DishResponse from(Dish dish) {
        return new DishResponse(dish.getDishId(), dish.getDishName(), dish.getImgUrl());
    }
}
