package core.startup.mealtoktok.domain.fulldining;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryId;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FullDining {

    private FullDiningId fullDiningId;
    private MealDeliveryId mealDeliveryId;
    private CollectingState collectState;
    private LocalDateTime collectedDateTime;

    public static FullDining create(MealDelivery mealDelivery) {
        return new FullDining(
                null, mealDelivery.getMealDeliveryId(), CollectingState.NOT_COLLECTED, null);
    }

    public void collectRequest() {
        this.collectState = CollectingState.COLLECT_REQUESTED;
    }

    public void collect() {
        this.collectState = CollectingState.COLLECTED;
        this.collectedDateTime = LocalDateTime.now();
    }
}
