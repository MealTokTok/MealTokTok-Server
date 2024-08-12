package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;
import java.util.List;

import core.startup.mealtoktok.domain.order.Orderer;

public interface FullDiningRepository {

    void saveAll(List<FullDining> fullDinings);

    void update(FullDining fullDining);

    FullDining find(TargetFullDining targetFullDining);

    List<FullDining> findAll(
            Orderer orderer, DeliveryState deliveryState, LocalDateTime validDateTime);

    int countByCollectingState(Orderer orderer, CollectingState collectingState);
}
