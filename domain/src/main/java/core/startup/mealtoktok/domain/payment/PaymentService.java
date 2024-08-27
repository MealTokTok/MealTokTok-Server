package core.startup.mealtoktok.domain.payment;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.order.OrderReader;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentValidator paymentValidator;
    private final PaymentGateway paymentGateway;
    private final PaymentAppender paymentAppender;
    private final OrderReader orderReader;

    public Payment pay(String paymentKey, OrderId orderId, Money amount) {
        Order order = orderReader.read(orderId);
        paymentValidator.validate(order, amount);
        Payment confirmedPayment = paymentGateway.confirm(paymentKey, orderId, amount);
        return paymentAppender.append(confirmedPayment);
    }

    public void fail(String failReason, OrderId orderId) {
        Payment failedPayment = Payment.fail(failReason, orderId);
        paymentAppender.append(failedPayment);
    }
}
