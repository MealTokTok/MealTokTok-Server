package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.File;
import core.startup.mealtoktok.domain.global.FileUploader;
import core.startup.mealtoktok.domain.global.ImageReader;
import core.startup.mealtoktok.domain.global.TargetImage;

@Service
@RequiredArgsConstructor
@Transactional
public class DishService {

    private final DishReader dishReader;
    private final DishRemover dishRemover;
    private final DishAppender dishAppender;
    private final DishUpdater dishUpdater;
    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final DishImageReader dishImageReader;
    private final FileUploader fileUploader;
    private final ImageReader imageReader;

    public void createDish(
            TargetDishStore targetDishStore,
            TargetDishCategory targetDishCategory,
            List<File> uploadFiles,
            DishInfo dishInfo) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        List<Image> images = fileUploader.upload(uploadFiles);
        dishAppender.append(dishStore, dishCategory, images, dishInfo);
    }

    public void deleteDish(TargetDish targetDish) {
        Dish dish = dishReader.read(targetDish);
        List<DishImage> dishImages = dishImageReader.readAll(targetDish);
        dishRemover.remove(dish, dishImages);
    }

    public void updateDish(TargetDish targetDish, List<File> uploadFiles, DishInfo dishInfo) {
        Dish dish = dishReader.read(targetDish);
        DishStore dishStore = dishStoreReader.read(TargetDishStore.from(dish.getDishStoreId()));
        List<Image> images = fileUploader.upload(uploadFiles);
        dishUpdater.update(dishStore, dish, images, dishInfo);
    }

    public List<DishAndImage> readDishes(
            TargetDishStore targetDishStore, TargetDishCategory targetDishCategory) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        List<Dish> dishes = dishReader.readAll(dishStore, dishCategory);

        return dishes.stream().map(this::convertToDishAndImage).toList();
    }

    private DishAndImage convertToDishAndImage(Dish dish) {
        DishImage dishImage = dishImageReader.read(TargetDish.from(dish.getDishId()));
        Image image = imageReader.read(TargetImage.from(dishImage.imageId()));
        return DishAndImage.of(dish, image);
    }

    public List<DishAndImage> searchDishes(TargetDishStore targetDishStore, String keyword) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        List<Dish> dishes = dishReader.search(dishStore, keyword);

        return dishes.stream().map(this::convertToDishAndImage).toList();
    }
}
