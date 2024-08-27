package core.startup.mealtoktok.api.payment.dto;

import core.startup.mealtoktok.domain.payment.Payment;
import core.startup.mealtoktok.domain.payment.PaymentMethod;
import core.startup.mealtoktok.domain.payment.PaymentProvider;
import core.startup.mealtoktok.domain.payment.PaymentState;

public record PaymentResponse(
        Long paymentId,
        String paymentKey,
        String orderId,
        Long payAmount,
        PaymentMethod paymentMethod,
        PaymentProvider paymentProvider,
        PaymentState paymentState) {

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getPaymentKey(),
                payment.getOrderId().value(),
                payment.getPayAmount().amount().longValue(),
                payment.getPaymentMethod(),
                payment.getPaymentProvider(),
                payment.getPaymentState());
    }
}
