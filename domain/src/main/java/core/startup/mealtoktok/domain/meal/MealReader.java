package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealReader {

    private final MealRepository mealRepository;

    public Meal read(TargetMeal targetMeal) {
        return mealRepository.findById(targetMeal);
    }
}
