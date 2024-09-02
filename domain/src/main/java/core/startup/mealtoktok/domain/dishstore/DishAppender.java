package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.File;
import core.startup.mealtoktok.domain.global.FileUploader;
import core.startup.mealtoktok.domain.global.ImageAppender;

@Component
@RequiredArgsConstructor
public class DishAppender {

    private final DishValidator dishValidator;
    private final DishRepository dishRepository;
    private final FileUploader fileUploader;
    private final ImageAppender imageAppender;

    public void append(
            DishStore dishStore,
            DishCategory dishCategory,
            DishContent dishContent,
            File uploadFile) {
        dishValidator.validateName(dishStore, dishContent.dishName());
        Image image = fileUploader.upload(uploadFile);
        Image saveImage = imageAppender.append(image);
        dishRepository.saveDish(dishStore, dishCategory, dishContent, saveImage);
    }
}
