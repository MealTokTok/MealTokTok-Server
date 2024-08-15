package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealAppender {

    private final MealRepository mealRepository;
    private final MealValidator mealValidator;

    public void append(MealOwner mealOwner, MealContent mealContent) {
        mealValidator.validate(mealContent);
        MealAndDishIds mealAndDishIds =
                MealAndDishIds.create(
                        Meal.of(mealOwner, mealContent.mealInfo()), mealContent.dishIds());
        mealRepository.save(mealAndDishIds);
    }
}
