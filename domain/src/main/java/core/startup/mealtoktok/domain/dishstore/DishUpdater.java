package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.File;
import core.startup.mealtoktok.domain.global.FileUploader;
import core.startup.mealtoktok.domain.global.ImageReader;
import core.startup.mealtoktok.domain.global.ImageUpdater;
import core.startup.mealtoktok.domain.global.TargetImage;

@Component
@RequiredArgsConstructor
@Transactional
public class DishUpdater {

    private final DishValidator dishValidator;
    private final FileUploader fileUploader;
    private final ImageReader imageReader;
    private final ImageUpdater imageUpdater;
    private final DishRepository dishRepository;

    public void update(DishStore dishStore, Dish dish, File uploadImage, DishContent dishContent) {
        dishValidator.validateName(dishStore, dish, dishContent.dishName());
        Image image = fileUploader.upload(uploadImage);
        Image existingImage = imageReader.read(TargetImage.from(dish.getDishImage().getId()));
        Image updatedImage = imageUpdater.update(existingImage, image);
        dishRepository.updateDish(dish, dishContent, updatedImage);
    }
}
