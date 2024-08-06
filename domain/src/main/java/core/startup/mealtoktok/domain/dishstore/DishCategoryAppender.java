package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishCategoryAppender {

    private final DishCategoryValidator dishCategoryValidator;
    private final DishRepository dishRepository;

    public void append(DishCategoryInfo dishCategoryInfo) {
        dishCategoryValidator.validate(dishCategoryInfo.categoryName());
        dishRepository.saveDishCategory(dishCategoryInfo);
    }
}
