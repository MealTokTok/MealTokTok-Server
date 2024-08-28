package core.startup.mealtoktok.domain.mealdelivery;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealDeliveryUpdater {

    private final MealDeliveryRepository mealDeliveryRepository;
}
