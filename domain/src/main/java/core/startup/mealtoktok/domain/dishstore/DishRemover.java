package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishRemover {

    private final DishRepository dishRepository;

    public void remove(Dish dish) {
        dishRepository.delete(dish);
    }
}