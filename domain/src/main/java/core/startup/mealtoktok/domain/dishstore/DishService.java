package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.global.File;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishReader dishReader;
    private final DishRemover dishRemover;
    private final DishAppender dishAppender;
    private final DishUpdater dishUpdater;
    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final DishWithImageFinder dishWithImageFinder;

    @Transactional
    public void createDish(
            TargetDishStore targetDishStore,
            TargetDishCategory targetDishCategory,
            DishContent dishContent,
            File uploadImage) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        dishAppender.append(dishStore, dishCategory, dishContent, uploadImage);
    }

    @Transactional
    public void deleteDish(TargetDish targetDish) {
        Dish dish = dishReader.read(targetDish);
        dishRemover.remove(dish);
    }

    @Transactional
    public void updateDish(TargetDish targetDish, File uploadImage, DishContent dishContent) {
        Dish dish = dishReader.read(targetDish);
        DishStore dishStore = dishStoreReader.read(TargetDishStore.from(dish.getDishStoreId()));
        dishUpdater.update(dishStore, dish, uploadImage, dishContent);
    }

    public List<DishWithImage> readDishes(TargetDishCategory targetDishCategory) {
        return dishWithImageFinder.find(targetDishCategory);
    }

    public List<DishWithImage> searchDishes(String keyword) {
        return dishWithImageFinder.find(keyword);
    }
}
