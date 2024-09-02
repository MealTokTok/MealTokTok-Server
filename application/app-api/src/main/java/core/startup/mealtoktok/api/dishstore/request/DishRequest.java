package core.startup.mealtoktok.api.dishstore.request;

import core.startup.mealtoktok.domain.dishstore.DishContent;

public record DishRequest(String dishName, int dishPrice, int dishQuantity) {

    public DishContent toContent() {
        return DishContent.of(dishName, dishPrice, dishQuantity);
    }
}
