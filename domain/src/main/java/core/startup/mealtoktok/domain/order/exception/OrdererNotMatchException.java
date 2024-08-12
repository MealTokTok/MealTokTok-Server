package core.startup.mealtoktok.domain.order.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class OrdererNotMatchException extends DomainException {

    public static final OrdererNotMatchException EXCEPTION = new OrdererNotMatchException();

    private OrdererNotMatchException() {
        super(OrderErrorCode.ORDERER_NOT_MATCH);
    }
}
