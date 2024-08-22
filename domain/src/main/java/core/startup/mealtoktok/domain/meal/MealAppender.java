package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealAppender {

    private final MealRepository mealRepository;
    private final MealValidator mealValidator;

    public void append(MealOwner mealOwner, MealContent newMealContent) {
        mealValidator.validate(newMealContent);
        MealAndDishIds mealAndDishIds =
                MealAndDishIds.create(
                        Meal.of(mealOwner, newMealContent.mealInfo()), newMealContent.dishIds());
        mealRepository.save(mealAndDishIds);
    }
}
