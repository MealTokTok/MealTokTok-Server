package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;

@Component
@RequiredArgsConstructor
public class DishAppender {

    private final DishValidator dishValidator;
    private final DishRepository dishRepository;

    public void append(
            DishStore dishStore,
            DishCategory dishCategory,
            List<Image> dishImages,
            DishInfo dishInfo) {
        dishValidator.validateName(dishStore, dishInfo.dishName());
        dishRepository.saveDish(dishStore, dishCategory, dishImages, dishInfo);
    }
}
