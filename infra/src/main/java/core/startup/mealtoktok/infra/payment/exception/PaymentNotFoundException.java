package core.startup.mealtoktok.infra.payment.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.payment.exception.PaymentErrorCode;

public class PaymentNotFoundException extends InfraException {

    public static final PaymentNotFoundException EXCEPTION = new PaymentNotFoundException();

    private PaymentNotFoundException() {
        super(PaymentErrorCode.PAYMENT_NOT_FOUND);
    }
}
