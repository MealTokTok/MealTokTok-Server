package core.startup.mealtoktok.domain.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderAppender {

    private final OrderRepository orderRepository;

    public TargetOrder append(Orderer orderer, OrderContent orderContent) {
        Order order =
                Order.create(
                        orderer,
                        orderContent.orderType(),
                        orderContent.specialInstruction(),
                        orderContent.orderPrice());
        return orderRepository.save(order);
    }
}
