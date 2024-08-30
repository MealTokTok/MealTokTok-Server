package core.startup.mealtoktok.domain.dishstore;

import java.math.BigDecimal;

import core.startup.mealtoktok.common.dto.Money;

public record DishInfo(String dishName, Money dishPrice, boolean isSoldOut) {

    public static DishInfo of(String dishName, int dishPrice, boolean isSoldOut) {
        return new DishInfo(dishName, Money.from(BigDecimal.valueOf(dishPrice)), isSoldOut);
    }
}
