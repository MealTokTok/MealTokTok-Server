package core.startup.mealtoktok.domain.payment;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.OrderId;

@Component
@RequiredArgsConstructor
public class PaymentReader {

    private final PaymentRepository paymentRepository;

    public Payment read(PaymentId paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public Payment read(OrderId orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
