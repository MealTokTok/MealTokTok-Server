package core.startup.mealtoktok.infra.payment.client;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.payment.Payment;
import core.startup.mealtoktok.domain.payment.PaymentGateway;
import core.startup.mealtoktok.infra.toss.client.TossPaymentClient;
import core.startup.mealtoktok.infra.toss.dto.TossCancelRequest;
import core.startup.mealtoktok.infra.toss.dto.TossConfirmRequest;
import core.startup.mealtoktok.infra.toss.dto.TossPayment;

@Component
@RequiredArgsConstructor
public class PaymentClient implements PaymentGateway {

    private final TossPaymentClient tossPaymentClient;

    @Override
    public Payment confirm(String paymentKey, OrderId orderId, Money money) {
        TossPayment confirmedPayment =
                tossPaymentClient.confirm(TossConfirmRequest.of(paymentKey, orderId, money));
        return confirmedPayment.toDomain();
    }

    @Override
    public void cancel(String paymentKey, String cancelReason) {
        tossPaymentClient.cancel(paymentKey, TossCancelRequest.from(cancelReason));
    }
}
