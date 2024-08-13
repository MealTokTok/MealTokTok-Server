package core.startup.mealtoktok.domain.dishstore;

import java.math.BigDecimal;

import core.startup.mealtoktok.domain.order.Money;

public record DishInfo(String dishName, Money dishPrice, String imgUrl) {
    public static DishInfo of(String dishName, int dishPrice, String imgUrl) {
        return new DishInfo(dishName, Money.from(BigDecimal.valueOf(dishPrice)), imgUrl);
    }
}
