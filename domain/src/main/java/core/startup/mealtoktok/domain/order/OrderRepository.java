package core.startup.mealtoktok.domain.order;

import java.util.List;

public interface OrderRepository {

    TargetOrder save(Order order);

    List<Order> findAll(Orderer orderer);

    Order find(TargetOrder targetOrder);
}
