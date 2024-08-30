package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.common.dto.Image;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class DishUpdater {

    private final DishValidator dishValidator;
    private final DishRepository dishRepository;
    private final DishImageReader dishImageReader;

    public void update(DishStore dishStore, Dish dish, List<Image> images, DishInfo dishInfo) {
        dishValidator.validateName(dishStore, dish, dishInfo.dishName());

        if(images!=null) {
            List<DishImage> dishImages = dishImageReader.read(
                    TargetDish.from(dish.getDishId()));
            dishRepository.deleteDishImages(dishImages);
            dishRepository.saveDishImages(dish, images);
        }

        dishRepository.updateDish(dish, dishInfo);
    }
}
