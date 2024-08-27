package core.startup.mealtoktok.domain.meal.exception;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.meal.MealDish;
import core.startup.mealtoktok.domain.meal.MealRepository;
import core.startup.mealtoktok.domain.meal.TargetMeal;

@Component
@RequiredArgsConstructor
public class MealDishReader {

    private final MealRepository mealRepository;

    public List<MealDish> read(TargetMeal targetMeal) {
        return mealRepository.findAllByMealId(targetMeal);
    }
}
