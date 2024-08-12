package core.startup.mealtoktok.api.mealdelivery.dto;

import java.time.LocalDateTime;

import core.startup.mealtoktok.domain.mealdelivery.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.FullDining;

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
