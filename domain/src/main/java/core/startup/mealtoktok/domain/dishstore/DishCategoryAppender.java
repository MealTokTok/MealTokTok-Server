package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

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
