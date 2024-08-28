package core.startup.mealtoktok.domain.payment;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentManager {

    private final PaymentRepository paymentRepository;

    public void cancel(Payment payment, String cancelReason) {
        Payment cancelled = payment.cancel(cancelReason);
        paymentRepository.update(cancelled);
    }
}
