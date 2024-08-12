package core.startup.mealtoktok.infra.order.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.OrderRepository;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.domain.order.TargetOrder;
import core.startup.mealtoktok.infra.order.entity.OrderEntity;
import core.startup.mealtoktok.infra.order.exception.OrderNotFoundException;

@Repository
@RequiredArgsConstructor
@Transactional
public class CoreOrderRepository implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public TargetOrder save(Order order) {
        OrderEntity savedEntity = orderJpaRepository.save(OrderEntity.from(order));
        return TargetOrder.from(savedEntity.getOrderId());
    }

    @Override
    public Order find(TargetOrder targetOrder) {
        return orderJpaRepository
                .findById(targetOrder.orderId())
                .map(OrderEntity::toDomain)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);
    }

    @Override
    public List<Order> findAll(Orderer orderer) {
        return orderJpaRepository.findAllByOrderer(orderer).parallelStream()
                .map(OrderEntity::toDomain)
                .toList();
    }
}
