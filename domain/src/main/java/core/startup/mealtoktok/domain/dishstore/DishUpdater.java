package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class DishUpdater {

    private final DishRepository dishRepository;

    public void update(Dish dish, DishInfo dishInfo) {
        dishRepository.update(dish, dishInfo);
    }
}
