package core.startup.mealtoktok.domain.order;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.user.DeliveryAddress;

@Component
@RequiredArgsConstructor
public class OrderAppender {

    private final OrderRepository orderRepository;

    public TargetOrder append(
            Orderer orderer, OrderContent orderContent, DeliveryAddress deliveryAddress) {
        Order order =
                Order.create(
                        orderer,
                        orderContent.orderType(),
                        orderContent.specialInstruction(),
                        orderContent.orderPrice(),
                        deliveryAddress,
                        orderContent.orderedMeals().size());
        return orderRepository.save(order);
    }
}
