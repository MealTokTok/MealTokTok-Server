package core.startup.mealtoktok.domain.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.exception.OrdererNotMatchException;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    public void validate(OrderContent orderContent) {}

    public void validate(Order order, Orderer orderer) {
        if (!order.isOrderBy(orderer)) {
            throw OrdererNotMatchException.EXCEPTION;
        }
    }
}
