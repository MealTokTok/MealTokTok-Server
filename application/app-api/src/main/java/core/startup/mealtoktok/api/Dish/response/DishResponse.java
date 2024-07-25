package core.startup.mealtoktok.api.dish.response;

import core.startup.mealtoktok.domain.dish.Dish;

public record DishResponse(
        Long dishId,
        String dishName,
        String imgUrl
) {
    public static DishResponse from(Dish dish){
        return new DishResponse(
                dish.getDishId(),
                dish.getDishName(),
                dish.getImgUrl()
        );
    }
}
