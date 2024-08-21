package core.startup.mealtoktok.domain.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryCountManager;

@Component
@RequiredArgsConstructor
public class OrderUpdater implements MealDeliveryCountManager {

    private final OrderRepository orderRepository;

    @Override
    public void decrease(Long orderId) {
        Order order = orderRepository.find(TargetOrder.from(orderId));
        order.completeOneDelivery();
        orderRepository.update(order);
    }
}
