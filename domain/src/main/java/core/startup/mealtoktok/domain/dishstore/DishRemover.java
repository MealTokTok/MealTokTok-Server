package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishRemover {

    private final DishRepository dishRepository;

    public void remove(Dish dish) {
        dishRepository.delete(dish);
    }
}
