package core.startup.mealtoktok.domain.meal;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealUpdater {

    private final MealValidator mealValidator;
    private final MealRepository mealRepository;

    public void update(
            MealOwner mealOwner, Meal meal, List<MealDish> mealDishes, MealContent mealContent) {
        mealValidator.validate(mealOwner, meal, mealContent);
        mealRepository.update(meal, mealDishes, mealContent);
    }
}
