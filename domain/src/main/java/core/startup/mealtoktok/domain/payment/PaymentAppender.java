package core.startup.mealtoktok.domain.payment;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentAppender {

    private final PaymentRepository paymentRepository;

    public Payment append(Payment payment) {
        return paymentRepository.save(payment);
    }
}
