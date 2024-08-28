package core.startup.mealtoktok.domain.payment;

import core.startup.mealtoktok.domain.order.OrderId;

public interface PaymentRepository {

    Payment save(Payment payment);

    Payment findById(PaymentId paymentId);

    void update(Payment payment);

    Payment findByOrderId(OrderId orderId);
}
