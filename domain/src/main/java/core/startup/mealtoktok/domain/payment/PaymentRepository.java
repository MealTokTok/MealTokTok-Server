package core.startup.mealtoktok.domain.payment;

public interface PaymentRepository {

    Payment save(Payment payment);

    Payment findById(PaymentId paymentId);

    void update(Payment payment);
}
