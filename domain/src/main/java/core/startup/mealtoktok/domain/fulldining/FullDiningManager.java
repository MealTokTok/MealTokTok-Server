package core.startup.mealtoktok.domain.fulldining;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;
import core.startup.mealtoktok.domain.order.FullDiningInfo;
import core.startup.mealtoktok.domain.order.FullDiningReserver;

@Component
@RequiredArgsConstructor
public class FullDiningManager implements FullDiningReserver {

    private final FullDiningRepository fullDiningRepository;

    public void reserve(List<FullDiningInfo> fullDiningInfos) {
        List<FullDining> fullDinings =
                fullDiningInfos.stream()
                        .filter(FullDiningInfo::hasFullDiningOption)
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
