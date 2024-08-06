package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class DishUpdater {

    private final DishValidator dishValidator;
    private final DishRepository dishRepository;

    public void update(DishStore dishStore, Dish dish, DishInfo dishInfo) {
        dishValidator.validateName(dishStore, dishInfo.dishName());
        dishRepository.update(dish, dishInfo);
    }
}
