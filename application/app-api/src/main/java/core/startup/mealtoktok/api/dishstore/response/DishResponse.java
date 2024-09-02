package core.startup.mealtoktok.api.dishstore.response;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.dishstore.DishState;
import core.startup.mealtoktok.domain.dishstore.DishWithImage;

public record DishResponse(
        Long dishId,
        String dishName,
        Money dishPrice,
        String imgUrl,
        int dishQuantity,
        DishState dishState) {

    public static DishResponse from(DishWithImage dishWithImage) {
        return new DishResponse(
                dishWithImage.dish().getDishId(),
                dishWithImage.dish().getDishName(),
                dishWithImage.dish().getDishPrice(),
                dishWithImage.image().getImageUrl(),
                dishWithImage.dish().getDishQuantity(),
                dishWithImage.dish().getDishState());
    }
}
