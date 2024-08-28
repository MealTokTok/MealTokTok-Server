package core.startup.mealtoktok.domain.fulldining;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.MealDeliveryId;
import core.startup.mealtoktok.domain.order.FullDiningInfo;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FullDining {

    private FullDiningId fullDiningId;
    private MealDeliveryId mealDeliveryId;
    private CollectingState collectState;
    private LocalDateTime collectedDateTime;

    public static FullDining create(FullDiningInfo fullDiningInfo) {
        return new FullDining(
                null,
                MealDeliveryId.from(fullDiningInfo.mealDeliveryId()),
                CollectingState.NOT_COLLECTED,
                null);
    }

    public void collectRequest() {
        this.collectState = CollectingState.COLLECT_REQUESTED;
    }

    public void collect() {
        this.collectState = CollectingState.COLLECTED;
        this.collectedDateTime = LocalDateTime.now();
    }
}
