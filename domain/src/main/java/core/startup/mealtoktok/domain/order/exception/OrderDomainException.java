package core.startup.mealtoktok.domain.order.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class OrderDomainException extends DomainException {

    public OrderDomainException(String message) {
        super(OrderErrorCode.ORDER_DOMAIN_ERROR.setMessage(message));
    }
}
