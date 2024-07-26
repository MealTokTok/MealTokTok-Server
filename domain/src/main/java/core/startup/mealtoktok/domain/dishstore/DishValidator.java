package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.domain.dishstore.exception.DishNameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishValidator {

    private final DishRepository dishRepository;
    public void validateName(DishStore dishStore, String dishName) {
        if(dishRepository.existsByDishStoreEntityAndDishName(dishStore, dishName)){
            throw DishNameAlreadyExistsException.EXCEPTION;
        }
    }
}
