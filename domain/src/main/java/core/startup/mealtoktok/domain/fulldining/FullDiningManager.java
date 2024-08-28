package core.startup.mealtoktok.domain.fulldining;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;

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

    public void collectRequest(FullDiningId fullDiningId, CollectingState collectingState) {
        FullDining fullDining = fullDiningRepository.findById(fullDiningId);
        switch (collectingState) {
            case COLLECT_REQUESTED -> fullDining.collectRequest();
            case COLLECTED -> fullDining.collect();
        }
        fullDiningRepository.update(fullDining);
    }

    public int countReturnableContainers(Recipient recipient, CollectingState collectingState) {
        return fullDiningRepository.countByCollectingState(recipient, collectingState);
    }

    public List<FullDining> getFullDinings(
            Recipient recipient, DeliveryState deliveryState, LocalDateTime validDateTime) {
        return fullDiningRepository.findAll(recipient, deliveryState, validDateTime);
    }
}
