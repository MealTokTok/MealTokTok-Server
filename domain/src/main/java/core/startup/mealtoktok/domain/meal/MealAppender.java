package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealAppender {

    private final MealRepository mealRepository;
    private final MealValidator mealValidator;

    public void append(MealDishes mealDishes) {
        mealValidator.validate(mealDishes);
        mealRepository.save(mealDishes);
    }
}
