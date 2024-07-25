package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.dishCategory.DishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishAppender {

    private final DishRepository dishRepository;

    public void append(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo) {
        dishRepository.save(dishStore, dishCategory, dishInfo);
    }

}
