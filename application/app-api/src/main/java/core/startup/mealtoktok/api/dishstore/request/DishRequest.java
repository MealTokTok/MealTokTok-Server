package core.startup.mealtoktok.api.dishstore.request;

import core.startup.mealtoktok.domain.dishstore.DishInfo;

public record DishRequest(String dishName, int dishPrice, boolean isSoldOut) {
    public DishInfo toDishInfo() {
        return DishInfo.of(dishName, dishPrice, isSoldOut);
    }
}
