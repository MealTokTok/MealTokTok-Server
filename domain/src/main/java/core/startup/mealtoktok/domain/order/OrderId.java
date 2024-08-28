package core.startup.mealtoktok.domain.order;

import core.startup.mealtoktok.common.domain.BaseId;

public class OrderId extends BaseId<String> {

    private OrderId(String value) {
        super(value);
    }

    public static OrderId from(String orderId) {
        return new OrderId(orderId);
    }
}
