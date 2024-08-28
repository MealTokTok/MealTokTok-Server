package core.startup.mealtoktok.api.fulldining.dto;

import java.time.LocalDateTime;

import core.startup.mealtoktok.domain.fulldining.CollectingState;
import core.startup.mealtoktok.domain.fulldining.FullDining;

public record FullDiningResponse(
        Long fullDiningId,
        Long mealDeliveryId,
        CollectingState collectState,
        LocalDateTime collectedDateTime) {

    public static FullDiningResponse from(FullDining fullDining) {
        return new FullDiningResponse(
                fullDining.getFullDiningId().getValue(),
                fullDining.getMealDeliveryId().getValue(),
                fullDining.getCollectState(),
                fullDining.getCollectedDateTime());
    }
}
