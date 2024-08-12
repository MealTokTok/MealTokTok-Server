package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.Orderer;

@Component
@RequiredArgsConstructor
public class FullDiningManager {

    private final FullDiningRepository fullDiningRepository;

    public void reserve(List<MealDelivery> mealDeliveries) {
        List<FullDining> fullDinings =
                mealDeliveries.parallelStream()
                        .filter(MealDelivery::hasFullDiningOption)
                        .map(FullDining::create)
                        .toList();
        fullDiningRepository.saveAll(fullDinings);
    }

    public void collectRequest(TargetFullDining targetFullDining, CollectingState collectingState) {
        FullDining fullDining = fullDiningRepository.find(targetFullDining);
        switch (collectingState) {
            case COLLECT_REQUESTED -> fullDining.collectRequest();
            case COLLECTED -> fullDining.collect();
        }
        fullDiningRepository.update(fullDining);
    }

    public int countReturnableContainers(Orderer orderer, CollectingState collectingState) {
        return fullDiningRepository.countByCollectingState(orderer, collectingState);
    }

    public List<FullDining> getFullDinings(
            Orderer orderer, DeliveryState deliveryState, LocalDateTime validDateTime) {
        return fullDiningRepository.findAll(orderer, deliveryState, validDateTime);
    }
}
