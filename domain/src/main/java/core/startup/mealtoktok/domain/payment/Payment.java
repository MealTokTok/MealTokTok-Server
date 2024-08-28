package core.startup.mealtoktok.domain.payment;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.payment.exception.PaymentDomainException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Payment {

    private PaymentId paymentId;
    private String paymentKey;
    private OrderId orderId;
    private Money payAmount;
    private PaymentMethod paymentMethod;
    private PaymentProvider paymentProvider;
    private PaymentState paymentState;
    private String failedReason;
    private String canceledReason;
    private LocalDateTime canceledAt;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;

    public static Payment fail(String failedReason, OrderId orderId) {
        return Payment.builder()
                .orderId(orderId)
                .paymentState(PaymentState.PAYMENT_FAILED)
                .failedReason(failedReason)
                .build();
    }

    public Payment cancel(String canceledReason) {
        if (!this.paymentState.equals(PaymentState.PAYMENT_COMPLETED)) {
            throw new PaymentDomainException("결제가 완료된 상태에서만 취소가 가능합니다.");
        }
        this.paymentState = PaymentState.PAYMENT_CANCELLED;
        this.canceledReason = canceledReason;
        this.canceledAt = LocalDateTime.now();
        return this;
    }
}
