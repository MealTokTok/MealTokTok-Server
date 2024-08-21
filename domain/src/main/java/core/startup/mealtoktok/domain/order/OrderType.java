package core.startup.mealtoktok.domain.order;

import core.startup.mealtoktok.domain.mealdelivery.OrderTypeForDelivery;

public enum OrderType {
    IMMEDIATE,
    SCHEDULED;

    public OrderTypeForDelivery toOrderTypeForDelivery() {
        return switch (this) {
            case IMMEDIATE -> OrderTypeForDelivery.IMMEDIATE;
            case SCHEDULED -> OrderTypeForDelivery.SCHEDULED;
        };
    }
}
