package core.startup.mealtoktok.domain.order;

import java.time.LocalDateTime;
import java.util.List;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;

public interface OrderRepository {

    void save(Order order);

    List<Order> findAll(Orderer orderer);

    Order find(OrderId orderId);

    SliceResult<Order> findByCondition(Orderer orderer, OrderSearchCond cond, Cursor cursor);

    void update(Order order);

    Integer countByOrderState(
            Orderer orderer, OrderState orderState, LocalDateTime startTime, LocalDateTime endTime);
}
