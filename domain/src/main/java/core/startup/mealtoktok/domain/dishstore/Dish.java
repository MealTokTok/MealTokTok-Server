package core.startup.mealtoktok.domain.dishstore;

import lombok.Builder;
import lombok.Getter;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.common.dto.Money;

@Getter
@Builder
public class Dish {

    private Long dishId;
    private Long dishStoreId;
    private Long dishCategoryId;
    private String dishName;
    private Money dishPrice;
    private int dishQuantity;
    private DishState dishState;
    private Image dishImage;

    public void reduceQuantity() {
        dishQuantity--;
        if (dishQuantity == 0) {
            dishState = DishState.SOLD_OUT;
        }
    }

    public void increaseQuantity() {
        dishQuantity++;
    }

    public void updateDishInfo(String dishName, Money dishPrice) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
    }
}
