package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.ImageReader;
import core.startup.mealtoktok.domain.global.TargetImage;

@Component
@RequiredArgsConstructor
public class DishAndImageWrapper {

    private final ImageReader imageReader;

    public DishAndImage wrap(Dish dish) {
        Image image = imageReader.read(TargetImage.from(dish.getDishImage().imageId()));
        return DishAndImage.of(dish, image);
    }

    public List<DishAndImage> wrapAll(List<Dish> dishes) {
        return dishes.stream().map(this::wrap).toList();
    }
}
