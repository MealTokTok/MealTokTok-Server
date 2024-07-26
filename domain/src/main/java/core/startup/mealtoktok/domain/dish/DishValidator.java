package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.dish.exception.DishNameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishValidator {

    private final DishRepository dishRepository;
    public void checkNoDuplicateDishName(DishStore dishStore, String dishName) {
        if(dishRepository.existsByDishStoreEntityAndDishName(dishStore, dishName)){
            throw DishNameAlreadyExistsException.EXCEPTION;
        }
    }
}
