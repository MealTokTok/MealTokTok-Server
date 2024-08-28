package core.startup.mealtoktok.domain.payment.exception;

import lombok.AllArgsConstructor;

import core.startup.mealtoktok.common.exception.BaseErrorCode;
import core.startup.mealtoktok.common.exception.ErrorReason;

@AllArgsConstructor
public enum PaymentErrorCode implements BaseErrorCode {
    PAYMENT_NOT_FOUND(404, "PAYMENT_404_1", "결제 정보를 찾을 수 없습니다"),
    PAYMENT_DOMAIN_ERROR(400, "PAYMENT_400_1"),
    PAYMENT_AMOUNT_MISMATCH(400, "PAYMENT_400_2", "주문금액과 결제금액이 일치하지 않습니다");

    private final Integer status;
    private final String errorCode;
    private String message;

    PaymentErrorCode(Integer status, String errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }

    public BaseErrorCode setMessage(String message) {
        this.message = message;
        return this;
    }
}
