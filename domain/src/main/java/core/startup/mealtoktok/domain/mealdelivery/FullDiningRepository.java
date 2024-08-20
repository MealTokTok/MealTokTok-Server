package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;
import java.util.List;

public interface FullDiningRepository {

    void saveAll(List<FullDining> fullDinings);

    void update(FullDining fullDining);

    FullDining find(TargetFullDining targetFullDining);

    List<FullDining> findAll(
            Recipient recipient, DeliveryState deliveryState, LocalDateTime validDateTime);

    int countByCollectingState(Recipient recipient, CollectingState collectingState);
}
