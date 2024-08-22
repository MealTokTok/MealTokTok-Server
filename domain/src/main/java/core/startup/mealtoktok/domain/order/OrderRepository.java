package core.startup.mealtoktok.domain.order;

import java.time.LocalDateTime;
import java.util.List;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;

public interface OrderRepository {

    TargetOrder save(Order order);

    List<Order> findAll(Orderer orderer);

    Order find(TargetOrder targetOrder);

    SliceResult<Order> findByCondition(Orderer orderer, OrderSearchCond cond, Cursor cursor);

    void update(Order order);

    Integer countByOrderState(
            Orderer orderer, OrderState orderState, LocalDateTime startTime, LocalDateTime endTime);
}
