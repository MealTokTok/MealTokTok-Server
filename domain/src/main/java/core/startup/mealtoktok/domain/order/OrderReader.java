package core.startup.mealtoktok.domain.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;

@Component
@RequiredArgsConstructor
public class OrderReader {

    private final OrderRepository orderRepository;

    public List<Order> read(Orderer orderer) {
        return orderRepository.findAll(orderer);
    }

    public Order read(OrderId orderId) {
        return orderRepository.findById(orderId);
    }

    public SliceResult<Order> read(Orderer orderer, OrderSearchCond cond, Cursor cursor) {
        return orderRepository.findByCondition(orderer, cond, cursor);
    }

    public Integer count(
            Orderer orderer,
            OrderState orderState,
            LocalDateTime startTime,
            LocalDateTime endTime) {
        return orderRepository.countByOrderState(orderer, orderState, startTime, endTime);
    }
}
