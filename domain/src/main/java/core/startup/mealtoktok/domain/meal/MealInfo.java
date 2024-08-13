package core.startup.mealtoktok.domain.meal;

import core.startup.mealtoktok.domain.order.Money;
import java.math.BigDecimal;

public record MealInfo(
        String mealName, Money mealPrice
) {
    public static MealInfo of(String mealName, int mealPrice) {
        return new MealInfo(mealName,  Money.from(BigDecimal.valueOf(mealPrice)));
    }
    public static MealInfo of(String mealName, Money mealPrice) {
        return new MealInfo(mealName, mealPrice);
    }
}
