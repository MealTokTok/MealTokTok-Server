package core.startup.mealtoktok.domain.dishstore;

import java.math.BigDecimal;

import core.startup.mealtoktok.common.dto.Money;

public record DishInfo(String dishName, Money dishPrice, int dishQuantity) {

    public static DishInfo of(String dishName, int dishPrice, int dishQuantity) {
        return new DishInfo(dishName, Money.from(BigDecimal.valueOf(dishPrice)), dishQuantity);
    }
}
