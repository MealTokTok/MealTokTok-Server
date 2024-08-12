package core.startup.mealtoktok.infra.mealdelivery.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import core.startup.mealtoktok.domain.mealdelivery.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.infra.mealdelivery.entity.FullDiningEntity;

public interface FullDiningJpaRepository extends JpaRepository<FullDiningEntity, Long> {

    @Query(
            """
            select fd from FullDiningEntity fd
            join MealDeliveryEntity md on fd.mealDeliveryId = md.mealDeliveryId
            join OrderEntity od on md.orderId = od.orderId
            where od.orderer = :orderer
            and md.deliveryState = :deliveryState
            and md.deliveryCompleteTime >= :validDateTime
            """)
    List<FullDiningEntity> findByOrdererAndDeliveryStateAndValidPeriod(
            Orderer orderer, DeliveryState deliveryState, LocalDateTime validDateTime);

    @Query(
            """
              select count(fd) from FullDiningEntity fd
              join MealDeliveryEntity md on fd.mealDeliveryId = md.mealDeliveryId
              join OrderEntity od on md.orderId = od.orderId
              where od.orderer = :orderer
              and fd.collectingState = :collectingState
            """)
    long countByOrdererAndCollectingState(Orderer orderer, CollectingState collectingState);
}
