package core.startup.mealtoktok.domain.fulldining;

import java.time.LocalDateTime;
import java.util.List;

import core.startup.mealtoktok.domain.mealdelivery.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;

public interface FullDiningRepository {

    void saveAll(List<FullDining> fullDinings);

    void update(FullDining fullDining);

    FullDining find(TargetFullDining targetFullDining);

    List<FullDining> findAll(
            Recipient recipient, DeliveryState deliveryState, LocalDateTime validDateTime);

    int countByCollectingState(Recipient recipient, CollectingState collectingState);
}
