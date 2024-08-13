package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealUpdater {

     private final MealValidator mealValidator;
     private final MealRepository mealRepository;

    public void update(Meal meal, MealDishes mealDishes) {
        mealValidator.validate(mealDishes);
        mealRepository.update(meal, mealDishes);
    }
}
