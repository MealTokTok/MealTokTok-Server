package core.startup.mealtoktok.infra.fulldining.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.fulldining.FullDining;
import core.startup.mealtoktok.domain.fulldining.FullDiningRepository;
import core.startup.mealtoktok.domain.fulldining.TargetFullDining;
import core.startup.mealtoktok.domain.mealdelivery.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;
import core.startup.mealtoktok.infra.fulldining.entity.FullDiningEntity;
import core.startup.mealtoktok.infra.fulldining.exception.FullDiningNotFoundException;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreFullDiningRepository implements FullDiningRepository {

    private final FullDiningJpaRepository fullDiningJpaRepository;

    @Override
    public FullDining find(TargetFullDining targetFullDining) {
        return fullDiningJpaRepository
                .findById(targetFullDining.fullDiningId())
                .map(FullDiningEntity::toDomain)
                .orElseThrow(() -> FullDiningNotFoundException.EXCEPTION);
    }

    @Override
    public List<FullDining> findAll(
            Recipient recipient, DeliveryState deliveryState, LocalDateTime validDateTime) {
        return fullDiningJpaRepository
                .findByOrdererAndDeliveryStateAndValidPeriod(
                        recipient, deliveryState, validDateTime)
                .stream()
                .map(FullDiningEntity::toDomain)
                .toList();
    }

    @Override
    public int countByCollectingState(Recipient recipient, CollectingState collectingState) {
        return (int)
                fullDiningJpaRepository.countByOrdererAndCollectingState(
                        recipient, collectingState);
    }

    @Override
    public void saveAll(List<FullDining> fullDinings) {
        List<FullDiningEntity> fullDiningEntities =
                fullDiningJpaRepository.saveAll(
                        fullDinings.stream().map(FullDiningEntity::from).toList());
        fullDiningJpaRepository.saveAll(fullDiningEntities);
    }

    @Override
    public void update(FullDining fullDining) {
        FullDiningEntity fullDiningEntity =
                fullDiningJpaRepository
                        .findById(fullDining.getFullDiningId())
                        .orElseThrow(() -> FullDiningNotFoundException.EXCEPTION);
        fullDiningEntity.update(fullDining);
    }
}