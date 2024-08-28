package core.startup.mealtoktok.infra.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Cursor;
import core.startup.mealtoktok.common.dto.SliceResult;
import core.startup.mealtoktok.domain.order.Order;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.order.OrderRepository;
import core.startup.mealtoktok.domain.order.OrderSearchCond;
import core.startup.mealtoktok.domain.order.OrderState;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.infra.jpa.util.PagingUtil;
import core.startup.mealtoktok.infra.order.entity.OrderEntity;
import core.startup.mealtoktok.infra.order.entity.OrdererVO;
import core.startup.mealtoktok.infra.order.exception.OrderNotFoundException;

@Repository
@RequiredArgsConstructor
@Transactional
public class CoreOrderRepository implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public void save(Order order) {
        orderJpaRepository.save(OrderEntity.from(order));
    }

    @Override
    public Order find(OrderId orderId) {
        return orderJpaRepository
                .findById(orderId.getValue())
                .map(OrderEntity::toDomain)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);
    }

    @Override
    public List<Order> findAll(Orderer orderer) {
        return orderJpaRepository.findAllByOrderer(OrdererVO.from(orderer)).parallelStream()
                .map(OrderEntity::toDomain)
                .toList();
    }

    @Override
    public SliceResult<Order> findByCondition(
            Orderer orderer, OrderSearchCond cond, Cursor cursor) {
        Slice<OrderEntity> orderEntities =
                orderJpaRepository.search(orderer, cond, PagingUtil.toPageRequest(cursor));

        return SliceResult.of(
                orderEntities.map(OrderEntity::toDomain).toList(), orderEntities.hasNext());
    }

    @Override
    public void update(Order order) {
        OrderEntity orderEntity =
                orderJpaRepository
                        .findById(order.getOrderId().getValue())
                        .orElseThrow(() -> OrderNotFoundException.EXCEPTION);
        orderEntity.update(order);
    }

    @Override
    public Integer countByOrderState(
            Orderer orderer,
            OrderState orderState,
            LocalDateTime startTime,
            LocalDateTime endTime) {
        return orderJpaRepository.countByOrdererAndOrderState(
                OrdererVO.from(orderer), orderState, startTime, endTime);
    }
}
