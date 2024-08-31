package core.startup.mealtoktok.api.dishstore.response;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.dishstore.DishAndImage;
import core.startup.mealtoktok.domain.dishstore.DishState;

public record DishResponse(
        Long dishId,
        String dishName,
        Money dishPrice,
        String imgUrl,
        int dishQuantity,
        DishState dishState) {

    public static DishResponse from(DishAndImage dishAndImage) {
        return new DishResponse(
                dishAndImage.dish().getDishId(),
                dishAndImage.dish().getDishName(),
                dishAndImage.dish().getDishPrice(),
                dishAndImage.image().getImageUrl(),
                dishAndImage.dish().getDishQuantity(),
                dishAndImage.dish().getDishState());
    }
}
