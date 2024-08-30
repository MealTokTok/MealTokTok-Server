package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.File;
import core.startup.mealtoktok.domain.global.FileUploader;
import java.util.List;

import javax.imageio.ImageReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

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

    public void createDish(
            TargetDishStore targetDishStore,
            TargetDishCategory targetDishCategory,
            List<File> uploadFiles,
            DishInfo dishInfo
    ) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        List<Image> images = fileUploader.upload(uploadFiles);
        dishAppender.append(dishStore, dishCategory, images, dishInfo);
    }

    public void deleteDish(TargetDish targetDish) {
        Dish dish = dishReader.read(targetDish);
        List<DishImage> dishImages = dishImageReader.read(targetDish);
        dishRemover.remove(dish, dishImages);
    }

    public void updateDish(TargetDish targetDish, List<File> uploadFiles, DishInfo dishInfo) {
        Dish dish = dishReader.read(targetDish);
        DishStore dishStore = dishStoreReader.read(TargetDishStore.from(dish.getDishStoreId()));
        List<Image> images = fileUploader.upload(uploadFiles);
        dishUpdater.update(dishStore, dish, images,  dishInfo);
    }

    public List<Dish> readDishes(
            TargetDishStore targetDishStore, TargetDishCategory targetDishCategory) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        return dishReader.readAll(dishStore, dishCategory);
    }

    public List<Dish> searchDishes(TargetDishStore targetDishStore, String keyword) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        return dishReader.search(dishStore, keyword);
    }
}
