package core.startup.mealtoktok.domain.payment.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class PaymentAmountMismatchException extends DomainException {

    public static final PaymentAmountMismatchException EXCEPTION =
            new PaymentAmountMismatchException();

    private PaymentAmountMismatchException() {
        super(PaymentErrorCode.PAYMENT_AMOUNT_MISMATCH);
    }
}
