package core.startup.mealtoktok.infra.order.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.order.exception.OrderErrorCode;

public class OrderNotFoundException extends InfraException {

    public static final OrderNotFoundException EXCEPTION = new OrderNotFoundException();

    private OrderNotFoundException() {
        super(OrderErrorCode.ORDER_NOT_FOUND);
    }
}
