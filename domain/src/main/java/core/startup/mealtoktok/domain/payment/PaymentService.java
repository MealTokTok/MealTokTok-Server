package core.startup.mealtoktok.domain.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Money;
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

    @Transactional
    public Payment pay(String paymentKey, OrderId orderId, Money payAmount) {
        Order order = orderReader.read(orderId);
        paymentValidator.validate(order, payAmount);
        Payment confirmedPayment = paymentGateway.confirm(paymentKey, orderId, payAmount);
        return paymentAppender.append(confirmedPayment);
    }

    public void fail(String failReason, OrderId orderId) {
        Payment failedPayment = Payment.fail(failReason, orderId);
        paymentAppender.append(failedPayment);
    }
}
