package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishRemover {

    private final DishRepository dishRepository;

    public void remove(Dish dish, List<DishImage> dishImages) {
        dishRepository.deleteDish(dish, dishImages);
    }
}
