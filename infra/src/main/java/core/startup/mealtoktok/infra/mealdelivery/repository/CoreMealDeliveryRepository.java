package core.startup.mealtoktok.infra.mealdelivery.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryRepository;
import core.startup.mealtoktok.domain.mealdelivery.TargetMealDelivery;
import core.startup.mealtoktok.domain.order.Orderer;
import core.startup.mealtoktok.domain.order.TargetOrder;
import core.startup.mealtoktok.infra.mealdelivery.entity.MealDeliveryEntity;
import core.startup.mealtoktok.infra.mealdelivery.exception.MealDeliveryNotFoundException;
import core.startup.mealtoktok.infra.order.exception.OrderNotFoundException;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreMealDeliveryRepository implements MealDeliveryRepository {

    private final MealDeliveryJpaRepository mealDeliveryJpaRepository;

    @Override
    public List<MealDelivery> saveAll(List<MealDelivery> mealDeliveries) {
        List<MealDeliveryEntity> mealDeliveryEntities =
                mealDeliveries.parallelStream().map(MealDeliveryEntity::from).toList();
        return mealDeliveryJpaRepository.saveAll(mealDeliveryEntities).parallelStream()
                .map(MealDeliveryEntity::toDomain)
                .toList();
    }

    @Override
    public void update(MealDelivery mealDelivery) {
        MealDeliveryEntity mealDeliveryEntity =
                mealDeliveryJpaRepository
                        .findById(mealDelivery.getMealDeliveryId())
                        .orElseThrow(() -> OrderNotFoundException.EXCEPTION);
        mealDeliveryEntity.update(mealDelivery);
    }

    @Override
    public MealDelivery find(TargetMealDelivery targetMealDelivery) {
        return mealDeliveryJpaRepository
                .findById(targetMealDelivery.mealDeliveryId())
                .map(MealDeliveryEntity::toDomain)
                .orElseThrow(() -> MealDeliveryNotFoundException.EXCEPTION);
    }

    @Override
    public MealDelivery findByOrdererAndDeliveryState(
            Orderer orderer, DeliveryState deliveryState) {
        return mealDeliveryJpaRepository
                .findByOrdererAndDeliveryState(orderer, deliveryState)
                .map(MealDeliveryEntity::toDomain)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);
    }

    @Override
    public List<MealDelivery> findAll(TargetOrder targetOrder) {
        return mealDeliveryJpaRepository.findAllByOrderId(targetOrder.orderId()).parallelStream()
                .map(MealDeliveryEntity::toDomain)
                .toList();
    }

    @Override
    public MealDelivery findByDeliveryStateAndTime(
            Orderer orderer,
            DeliveryState deliveryState,
            LocalDateTime startTime,
            LocalDateTime endTime) {
        return mealDeliveryJpaRepository
                .findByOrdererAndDeliveryStateAndTime(orderer, deliveryState, startTime, endTime)
                .map(MealDeliveryEntity::toDomain)
                .orElseThrow(() -> OrderNotFoundException.EXCEPTION);
    }
}
