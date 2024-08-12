package core.startup.mealtoktok.domain.mealdelivery;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.order.OrderedMeal;
import core.startup.mealtoktok.domain.order.TargetOrder;

@Component
@RequiredArgsConstructor
public class MealDeliveryReserver {

    private final MealDeliveryRepository mealDeliveryRepository;

    public List<MealDelivery> reserve(TargetOrder targetOrder, List<OrderedMeal> orderedMeals) {
        List<MealDelivery> mealDeliveries =
                orderedMeals.parallelStream()
                        .map(orderedMeal -> MealDelivery.create(targetOrder, orderedMeal))
                        .toList();
        return mealDeliveryRepository.saveAll(mealDeliveries);
    }
}
