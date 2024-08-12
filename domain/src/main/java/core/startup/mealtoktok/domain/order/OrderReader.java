package core.startup.mealtoktok.domain.order;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderReader {

    private final OrderRepository orderRepository;

    public List<Order> read(Orderer orderer) {
        return orderRepository.findAll(orderer);
    }

    public Order read(TargetOrder targetOrder) {
        return orderRepository.find(targetOrder);
    }
}
