package core.startup.mealtoktok.domain.payment.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class PaymentDomainException extends DomainException {

    public PaymentDomainException(String message) {
        super(PaymentErrorCode.PAYMENT_DOMAIN_ERROR.setMessage(message));
    }
}
