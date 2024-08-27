package core.startup.mealtoktok.domain.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryCountManager;

@Component
@RequiredArgsConstructor
public class OrderUpdater implements MealDeliveryCountManager {

    private final OrderRepository orderRepository;

    @Override
    public void decrease(String orderId) {
        Order order = orderRepository.find(OrderId.from(orderId));
        order.completeOneDelivery();
        orderRepository.update(order);
    }
}
