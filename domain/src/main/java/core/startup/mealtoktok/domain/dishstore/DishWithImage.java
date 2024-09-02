package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.common.dto.Image;

public record DishWithImage(Dish dish, Image image) {

    public static DishWithImage of(Dish dish, Image images) {
        return new DishWithImage(dish, images);
    }
}
