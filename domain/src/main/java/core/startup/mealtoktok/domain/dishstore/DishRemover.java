package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.ImageReader;
import core.startup.mealtoktok.domain.global.ImageRemover;
import core.startup.mealtoktok.domain.global.TargetImage;

@Component
@RequiredArgsConstructor
public class DishRemover {

    private final DishRepository dishRepository;

    public void remove(Dish dish) {
        dishRepository.deleteDish(dish);
    }
}
