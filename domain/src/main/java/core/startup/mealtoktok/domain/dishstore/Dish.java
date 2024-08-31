package core.startup.mealtoktok.domain.dishstore;

import lombok.Builder;
import lombok.Getter;

import core.startup.mealtoktok.common.dto.Money;

@Getter
@Builder
public class Dish {

    private Long dishId;
    private String dishName;
    private Money dishPrice;
    private DishState dishState;
    private int dishQuantity;
    private Long dishStoreId;
    private Long dishCategoryId;
    private DishImage dishImage;

    public void addDishImage(DishImage dishImage) {
        this.dishImage = dishImage;
    }
}
