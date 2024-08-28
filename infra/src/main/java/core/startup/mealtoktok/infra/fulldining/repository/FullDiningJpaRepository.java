package core.startup.mealtoktok.infra.fulldining.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import core.startup.mealtoktok.domain.fulldining.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;
import core.startup.mealtoktok.infra.fulldining.entity.FullDiningEntity;

public interface FullDiningJpaRepository extends JpaRepository<FullDiningEntity, Long> {

    @Query(
            """
                    select fd from FullDiningEntity fd
                    join MealDeliveryEntity md on fd.mealDeliveryId = md.mealDeliveryId
                    join OrderEntity od on md.orderId = od.orderId
                    where od.orderer.userId = :#{#recipient.userId()}
                    and md.deliveryState = :deliveryState
                    and md.deliveryDateTime.deliveryCompleteTime >= :validDateTime
                    """)
    List<FullDiningEntity> findByOrdererAndDeliveryStateAndValidPeriod(
            Recipient recipient, DeliveryState deliveryState, LocalDateTime validDateTime);

    @Query(
            """
                      select count(fd) from FullDiningEntity fd
                      join MealDeliveryEntity md on fd.mealDeliveryId = md.mealDeliveryId
                      join OrderEntity od on md.orderId = od.orderId
                      where od.orderer = :#{#recipient.userId()}
                      and fd.collectingState = :collectingState
                    """)
    long countByOrdererAndCollectingState(Recipient recipient, CollectingState collectingState);
}
