package core.startup.mealtoktok.domain.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.global.IdGenerator;
import core.startup.mealtoktok.domain.user.DeliveryAddress;

@Component
@RequiredArgsConstructor
public class OrderAppender {

    private final IdGenerator orderIdGenerator;
    private final OrderRepository orderRepository;

    public OrderId append(
            Orderer orderer, OrderContent orderContent, DeliveryAddress deliveryAddress) {
        OrderId orderId = OrderId.from(orderIdGenerator.generate().toString());
        Order order =
                Order.create(
                        orderId,
                        orderer,
                        orderContent.orderType(),
                        orderContent.specialInstruction(),
                        orderContent.orderPrice(),
                        deliveryAddress,
                        orderContent.orderedMeals().size());
        orderRepository.save(order);
        return orderId;
    }
}
