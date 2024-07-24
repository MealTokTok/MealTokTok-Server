package core.startup.mealtoktok.api.Dish.request;

import core.startup.mealtoktok.domain.dish.DishInfo;

public record CreateDishRequest(
        String dishName,
        String imgUrl
) {
    public DishInfo toDishInfo(){
        return DishInfo.of(dishName, imgUrl);
    }
}
