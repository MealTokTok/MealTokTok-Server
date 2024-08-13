package core.startup.mealtoktok.domain.meal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealValidator {

    private final MealRepository mealRepository;

    public void validate(MealDishes mealDishes) {
        if (mealRepository.exitsByMealName(mealDishes.mealInfo().mealName())) {
            throw new IllegalArgumentException("이미 존재하는 도시락 이름입니다.");
        }

        if (mealDishes.dishIds().size() != 4) {
            throw new IllegalArgumentException("도시락은 4개의 반찬을 가지고 있어야 합니다.");
        }
    }
}
