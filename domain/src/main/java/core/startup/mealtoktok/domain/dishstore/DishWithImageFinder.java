package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.ImageReader;
import core.startup.mealtoktok.domain.global.TargetImage;

@Component
@RequiredArgsConstructor
public class DishWithImageFinder {

    private final DishCategoryReader dishCategoryReader;
    private final DishReader dishReader;
    private final ImageReader imageReader;

    public DishWithImage find(TargetDish targetDish) {
        Dish dish = dishReader.read(targetDish);
        return wrap(dish);
    }

    public List<DishWithImage> find(TargetDishCategory targetDishCategory) {
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        List<Dish> dishes = dishReader.readAll(dishCategory);
        return dishes.stream().map(this::wrap).toList();
    }

    public List<DishWithImage> find(String keyword) {
        List<Dish> dishes = dishReader.search(keyword);
        return dishes.stream().map(this::wrap).toList();
    }

    private DishWithImage wrap(Dish dish) {
        Image image = imageReader.read(TargetImage.from(dish.getDishImage().imageId()));
        return DishWithImage.of(dish, image);
    }
}
