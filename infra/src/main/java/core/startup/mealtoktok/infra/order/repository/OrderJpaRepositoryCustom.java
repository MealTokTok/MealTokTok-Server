package core.startup.mealtoktok.infra.order.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import core.startup.mealtoktok.domain.order.OrderSearchCond;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.infra.order.entity.OrderEntity;

public interface OrderJpaRepositoryCustom {

    Slice<OrderEntity> search(Orderer orderer, OrderSearchCond cond, Pageable pageable);
}
