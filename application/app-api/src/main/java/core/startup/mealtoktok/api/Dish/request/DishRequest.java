package core.startup.mealtoktok.api.dish.request;

import core.startup.mealtoktok.domain.dish.DishInfo;

public record DishRequest(
        String dishName,
        String imgUrl
) {
    public DishInfo toDishInfo(){
        return DishInfo.of(dishName, imgUrl);
    }
}
