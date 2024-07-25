package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.dish.exception.DishNameAlreadyExistsException;
import core.startup.mealtoktok.domain.dishCategory.DishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DishReader {

    private final DishRepository dishRepository;

    public Dish read(TargetDish targetDish){
        return dishRepository.findById(targetDish);
    }

    public List<Dish> readAll(DishStore dishStore, DishCategory dishCategory) {
        return dishRepository.findAllByStoreAndCategory(dishStore, dishCategory);
    }

    public void checkNoDuplicateDishName(DishStore dishStore, String dishName) {
        if(dishRepository.existsByDishStoreEntityAndDishName(dishStore, dishName)){
            throw new DishNameAlreadyExistsException();
        }
    }
}
