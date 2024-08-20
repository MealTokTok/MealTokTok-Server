package core.startup.mealtoktok.infra.order.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.OrderPrice;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class OrderPriceVO {

    @Convert(converter = MoneyConverter.class, attributeName = "mealPrice")
    private Money mealPrice;

    @Convert(converter = MoneyConverter.class, attributeName = "deliveryPrice")
    private Money deliveryPrice;

    @Convert(converter = MoneyConverter.class, attributeName = "fullServicePrice")
    private Money fullServicePrice;

    @Convert(converter = MoneyConverter.class, attributeName = "totalPrice")
    private Money totalPrice;

    public static OrderPriceVO from(OrderPrice orderPrice) {
        return new OrderPriceVO(
                orderPrice.mealPrice(),
                orderPrice.deliveryPrice(),
                orderPrice.fullServicePrice(),
                orderPrice.totalPrice());
    }

    public OrderPrice toDomain() {
        return new OrderPrice(mealPrice, deliveryPrice, fullServicePrice, totalPrice);
    }
}
