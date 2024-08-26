package core.startup.mealtoktok.domain.fulldining;

import static core.startup.mealtoktok.common.consts.MealTokTokConstant.VALID_DATE_TIME;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.Recipient;

@Service
@RequiredArgsConstructor
public class FullDiningService {

    private final FullDiningManager fullDiningManager;

    public void changeCollectingState(
            TargetFullDining targetFullDining, CollectingState collectingState) {
        fullDiningManager.collectRequest(targetFullDining, collectingState);
        // TODO :알림 발송 alarmSender.send(targetFullDining, collectingState);
    }

    public int countCollectRequestContainers(Recipient recipient) {
        return fullDiningManager.countReturnableContainers(
                recipient, CollectingState.COLLECT_REQUESTED);
    }

    public List<FullDining> getFullDinings(Recipient recipient) {
        return fullDiningManager.getFullDinings(
                recipient, DeliveryState.DELIVERED, VALID_DATE_TIME);
    }
}
