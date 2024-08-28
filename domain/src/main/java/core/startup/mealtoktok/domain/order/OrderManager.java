package core.startup.mealtoktok.domain.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderManager {

    private final OrderRepository orderRepository;

    public void reduceRemainDeliveryCount(Order order) {
        order.completeOneDelivery();
        orderRepository.update(order);
    }

    public void cancelOrder(Order order) {
        order.cancelPayment();
        orderRepository.update(order);
    }

    public void startDelivery(Order order) {
        order.startDelivery();
        orderRepository.update(order);
    }

    public void completePayment(Order order) {
        order.completePayment();
        orderRepository.update(order);
    }

    public void acceptOrder(Order order) {
        order.accept();
        orderRepository.update(order);
    }
}
