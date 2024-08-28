package core.startup.mealtoktok.infra.payment.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.payment.Payment;
import core.startup.mealtoktok.domain.payment.PaymentId;
import core.startup.mealtoktok.domain.payment.PaymentRepository;
import core.startup.mealtoktok.infra.payment.entity.PaymentEntity;

@Repository
@RequiredArgsConstructor
public class CorePaymentRepository implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(PaymentEntity.from(payment)).toDomain();
    }

    @Override
    public Payment findById(PaymentId paymentId) {
        return null;
    }
}
