package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishCategoryUpdater {

    private final DishCategoryValidator dishCategoryValidator;
    private final DishRepository dishRepository;

    public void update(DishCategory dishCategory, DishCategoryInfo dishCategoryInfo) {
        dishCategoryValidator.validate(dishCategoryInfo.categoryName());
        dishRepository.updateDishCategory(dishCategory, dishCategoryInfo);
    }
}
