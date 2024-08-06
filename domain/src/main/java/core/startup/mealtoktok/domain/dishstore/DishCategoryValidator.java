package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.domain.dishstore.exception.DishCategoryNameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishCategoryValidator {

    private final DishRepository dishRepository;

    public void validate(String dishCategoryName) {
        if (dishRepository.existsByDishCategoryName(dishCategoryName)) {
            throw DishCategoryNameAlreadyExistsException.EXCEPTION;
        }
    }
}
