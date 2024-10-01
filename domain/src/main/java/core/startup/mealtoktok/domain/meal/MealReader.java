package core.startup.mealtoktok.domain.meal;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealReader {

    private final MealRepository mealRepository;

    public Meal read(TargetMeal targetMeal) {
        return mealRepository.findById(targetMeal);
    }

    public List<Meal> read(MealOwner mealOwner) {
        return mealRepository.findAllByMealOwner(mealOwner);
    }

    public Meal readActiveMeal(TargetMeal targetMeal) {
        return mealRepository.findActiveMealById(targetMeal);
    }
}
