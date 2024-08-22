package core.startup.mealtoktok.infra.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import core.startup.mealtoktok.domain.order.OrderState;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.infra.order.entity.OrderEntity;
import core.startup.mealtoktok.infra.order.entity.OrdererVO;

public interface OrderJpaRepository
        extends JpaRepository<OrderEntity, Long>, OrderJpaRepositoryCustom {

    List<OrderEntity> findAllByOrderer(Orderer orderer);

    @Query(
            """
            SELECT count(order)
            FROM OrderEntity order
            WHERE order.orderer = :orderer
            AND order.orderState = :orderState
            AND order.createdAt BETWEEN :startTime AND :endTime
            """)
    Integer countByOrdererAndOrderState(
            OrdererVO orderer,
            OrderState orderState,
            LocalDateTime startTime,
            LocalDateTime endTime);
}
