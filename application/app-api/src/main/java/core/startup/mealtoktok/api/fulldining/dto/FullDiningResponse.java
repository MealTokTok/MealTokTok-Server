package core.startup.mealtoktok.api.fulldining.dto;

import java.time.LocalDateTime;

import core.startup.mealtoktok.domain.fulldining.FullDining;
import core.startup.mealtoktok.domain.mealdelivery.CollectingState;

public record FullDiningResponse(
        Long fullDiningId,
        Long mealDeliveryId,
        CollectingState collectState,
        LocalDateTime collectedDateTime) {

    public static FullDiningResponse from(FullDining fullDining) {
        return new FullDiningResponse(
                fullDining.getFullDiningId(),
                fullDining.getMealDeliveryId(),
                fullDining.getCollectState(),
                fullDining.getCollectedDateTime());
    }
}
