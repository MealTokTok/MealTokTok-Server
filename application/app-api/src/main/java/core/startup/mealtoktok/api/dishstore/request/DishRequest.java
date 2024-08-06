package core.startup.mealtoktok.api.dishstore.request;

import core.startup.mealtoktok.domain.dishstore.DishInfo;

public record DishRequest(String dishName, String imgUrl) {
    public DishInfo toDishInfo() {
        return DishInfo.of(dishName, imgUrl);
    }
}
