package core.startup.mealtoktok.domain.payment;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.payment.exception.PaymentAmountMismatchException;

@Component
@RequiredArgsConstructor
public class PaymentValidator {

    public void validate(Order order, Money amount) {
        if (order.getOrderPrice().totalPrice().equals(amount)) {
            throw PaymentAmountMismatchException.EXCEPTION;
        }
    }
}
