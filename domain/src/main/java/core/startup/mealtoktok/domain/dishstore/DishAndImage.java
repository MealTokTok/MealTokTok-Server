package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.common.dto.Image;

public record DishAndImage(Dish dish, Image image) {
    public static DishAndImage of(Dish dish, Image images) {
        return new DishAndImage(dish, images);
    }
}
