package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.dishstore.exception.DishNameAlreadyExistsException;

@Component
@RequiredArgsConstructor
public class DishValidator {

    private final DishRepository dishRepository;

    public void validateName(DishStore dishStore, String dishName) {
        if (dishRepository.existsByDishStoreIdAndDishName(dishStore, dishName)) {
            throw DishNameAlreadyExistsException.EXCEPTION;
        }
    }

    public void validateName(DishStore dishStore, Dish dish, String dishName) {
        if (dishRepository.existsByNameExcludingTargetDish(dishStore, dish, dishName)) {
            throw DishNameAlreadyExistsException.EXCEPTION;
        }
    }
}
