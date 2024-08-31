package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.ImageAppender;

@Component
@RequiredArgsConstructor
public class DishAppender {

    private final DishValidator dishValidator;
    private final DishRepository dishRepository;
    private final ImageAppender imageAppender;

    public void append(
            DishStore dishStore, DishCategory dishCategory, Image image, DishInfo dishInfo) {
        dishValidator.validateName(dishStore, dishInfo.dishName());
        Image saveImage = imageAppender.append(image);
        dishRepository.saveDish(dishStore, dishCategory, dishInfo, saveImage);
    }
}
