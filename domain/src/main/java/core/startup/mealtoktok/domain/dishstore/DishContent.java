package core.startup.mealtoktok.domain.dishstore;

import java.math.BigDecimal;

import core.startup.mealtoktok.common.dto.Money;

public record DishContent(String dishName, Money dishPrice, int dishQuantity) {

    public static DishContent of(String dishName, int dishPrice, int dishQuantity) {
        return new DishContent(dishName, Money.from(BigDecimal.valueOf(dishPrice)), dishQuantity);
    }
}
