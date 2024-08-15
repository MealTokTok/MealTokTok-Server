package core.startup.mealtoktok.domain.mealdelivery;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealDeliveryReserver {

    private final MealDeliveryRepository mealDeliveryRepository;

    public List<MealDelivery> reserve(List<MealDelivery> mealDeliveries) {
        return mealDeliveryRepository.saveAll(mealDeliveries);
    }
}
