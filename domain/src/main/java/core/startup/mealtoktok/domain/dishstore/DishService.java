package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.File;
import core.startup.mealtoktok.domain.global.FileUploader;
import core.startup.mealtoktok.domain.global.ImageReader;
import core.startup.mealtoktok.domain.global.ImageRemover;
import core.startup.mealtoktok.domain.global.TargetImage;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishReader dishReader;
    private final DishRemover dishRemover;
    private final DishAppender dishAppender;
    private final DishUpdater dishUpdater;
    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final FileUploader fileUploader;
    private final ImageReader imageReader;
    private final ImageRemover imageRemover;
    private final DishAndImageWrapper dishAndImageWrapper;

    @Transactional
    public void createDish(
            TargetDishStore targetDishStore,
            TargetDishCategory targetDishCategory,
            File uploadFile,
            DishInfo dishInfo) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        Image image = fileUploader.upload(uploadFile);
        dishAppender.append(dishStore, dishCategory, image, dishInfo);
    }

    @Transactional
    public void deleteDish(TargetDish targetDish) {
        Dish dish = dishReader.read(targetDish);
        Image image = imageReader.read(TargetImage.from(dish.getDishImage().imageId()));
        dishRemover.remove(dish);
        imageRemover.remove(image);
    }

    @Transactional
    public void updateDish(TargetDish targetDish, File uploadFile, DishInfo dishInfo) {
        Dish dish = dishReader.read(targetDish);
        DishStore dishStore = dishStoreReader.read(TargetDishStore.from(dish.getDishStoreId()));
        Image image = fileUploader.upload(uploadFile);
        dishUpdater.update(dishStore, dish, image, dishInfo);
    }

    public List<DishAndImage> readDishes(TargetDishCategory targetDishCategory) {
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        List<Dish> dishes = dishReader.readAll(dishCategory);

        return dishAndImageWrapper.wrapAll(dishes);
    }

    public List<DishAndImage> searchDishes(String keyword) {
        List<Dish> dishes = dishReader.search(keyword);

        return dishAndImageWrapper.wrapAll(dishes);
    }
}
