package core.startup.mealtoktok.domain.meal;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealDishReader {

    private final MealRepository mealRepository;

    public List<MealDish> read(TargetMeal targetMeal) {
        return mealRepository.findAllMealDishByMealId(targetMeal);
    }
}
