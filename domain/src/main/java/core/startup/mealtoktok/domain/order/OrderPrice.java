package core.startup.mealtoktok.domain.order;

import java.math.BigDecimal;

import core.startup.mealtoktok.common.dto.Money;

public record OrderPrice(
        Money mealPrice, Money deliveryPrice, Money fullServicePrice, Money totalPrice) {

    public static OrderPrice of(
            int mealPrice, int deliveryPrice, int fullServicePrice, int totalPrice) {
        return new OrderPrice(
                Money.from(BigDecimal.valueOf(mealPrice)),
                Money.from(BigDecimal.valueOf(deliveryPrice)),
                Money.from(BigDecimal.valueOf(fullServicePrice)),
                Money.from(BigDecimal.valueOf(totalPrice)));
    }
}
