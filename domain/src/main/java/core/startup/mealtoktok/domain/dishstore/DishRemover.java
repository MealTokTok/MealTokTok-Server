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
    private final ImageReader imageReader;
    private final ImageRemover imageRemover;

    public void remove(Dish dish) {
        dishRepository.deleteDish(dish);
        Image image = imageReader.read(TargetImage.from(dish.getDishImage().imageId()));
        imageRemover.remove(image);
    }
}
