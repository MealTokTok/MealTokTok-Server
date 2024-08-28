package core.startup.mealtoktok.domain.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderManager {

    private final OrderRepository orderRepository;

    public void cancelOrder(Order order) {
        order.cancelPayment();
        orderRepository.update(order);
    }

    public void reduceDeliveryCount(OrderId orderId) {
        Order order = orderRepository.findById(orderId);
        order.completeOneDelivery();
        orderRepository.update(order);
    }
}
