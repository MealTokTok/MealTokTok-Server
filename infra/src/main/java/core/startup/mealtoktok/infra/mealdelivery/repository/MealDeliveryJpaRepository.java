package core.startup.mealtoktok.infra.mealdelivery.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.infra.mealdelivery.entity.MealDeliveryEntity;

public interface MealDeliveryJpaRepository extends JpaRepository<MealDeliveryEntity, Long> {

    List<MealDeliveryEntity> findAllByOrderId(Long orderId);

    @Query(
            """
            SELECT mealDelivery
            FROM MealDeliveryEntity mealDelivery
            JOIN OrderEntity oe on oe.orderId = mealDelivery.orderId
            WHERE oe.orderer = :orderer
            AND mealDelivery.deliveryState = :deliveryState
            """)
    Optional<MealDeliveryEntity> findByOrdererAndDeliveryState(
            Orderer orderer, DeliveryState deliveryState);

    @Query(
            """
            SELECT mealDelivery
            FROM MealDeliveryEntity mealDelivery
            JOIN OrderEntity oe on oe.orderId = mealDelivery.orderId
            WHERE oe.orderer = :orderer
            AND mealDelivery.deliveryState = :deliveryState
            AND mealDelivery.deliveryCompleteTime between :startTime and :endTime
            """)
    Optional<MealDeliveryEntity> findByOrdererAndDeliveryStateAndTime(
            Orderer orderer,
            DeliveryState deliveryState,
            LocalDateTime startTime,
            LocalDateTime endTime);
}
