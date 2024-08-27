package core.startup.mealtoktok.domain.payment;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.OrderId;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Payment {

    private Long paymentId;
    private String paymentKey;
    private OrderId orderId;
    private Money payAmount;
    private PaymentMethod paymentMethod;
    private PaymentProvider paymentProvider;
    private PaymentState paymentState;
    private String failedReason;
    private String canceledReason;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;

    public static Payment fail(String failedReason, OrderId orderId) {
        return Payment.builder()
                .orderId(orderId)
                .paymentState(PaymentState.PAYMENT_FAILED)
                .failedReason(failedReason)
                .build();
    }
}
