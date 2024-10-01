package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealRemover {

    private final MealRepository mealRepository;
    private final MealValidator mealValidator;

    public void remove(MealOwner mealOwner, Meal meal) {
        mealValidator.validate(mealOwner, meal);
        mealRepository.delete(meal);
    }
}
