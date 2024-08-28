package core.startup.mealtoktok.domain.payment;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.PaymentCanceler;

@Component
@RequiredArgsConstructor
public class PaymentManager implements PaymentCanceler {

    private final PaymentReader paymentReader;
    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;

    public void cancel(Order order, String cancelReason) {
        Payment payment = paymentReader.read(order.getOrderId());
        paymentGateway.cancel(payment.getPaymentKey(), cancelReason);
        Payment cancelled = payment.cancel(cancelReason);
        paymentRepository.update(cancelled);
    }
}
