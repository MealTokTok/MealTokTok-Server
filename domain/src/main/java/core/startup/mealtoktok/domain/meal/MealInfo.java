package core.startup.mealtoktok.domain.meal;

import java.math.BigDecimal;

import core.startup.mealtoktok.common.dto.Money;

public record MealInfo(String mealName, Money mealPrice, boolean isDeleted) {

    public static MealInfo of(String mealName, int mealPrice, boolean isDeleted) {
        return new MealInfo(mealName, Money.from(BigDecimal.valueOf(mealPrice)), isDeleted);
    }

    public static MealInfo of(String mealName, Money mealPrice, boolean isDeleted) {
        return new MealInfo(mealName, mealPrice, isDeleted);
    }
}
