package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.exception.DishCategoryNameAlreadyExistsException;

@Component
@RequiredArgsConstructor
public class DishCategoryValidator {

    private final DishRepository dishRepository;

    public void validate(String dishCategoryName) {
        if (dishRepository.existsByDishCategoryName(dishCategoryName)) {
            throw DishCategoryNameAlreadyExistsException.EXCEPTION;
        }
    }

    public void validate(DishCategory dishCategory, String dishCategoryName) {
        if (dishRepository.existsByNameExcludingTargetCategory(dishCategory, dishCategoryName)) {
            throw DishCategoryNameAlreadyExistsException.EXCEPTION;
        }
    }
}
