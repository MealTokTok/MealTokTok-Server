package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

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

    public void createDish(
            TargetDishStore targetDishStore,
            TargetDishCategory targetDishCategory,
            DishInfo dishInfo) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        dishAppender.append(dishStore, dishCategory, dishInfo);
    }

    public void deleteDish(TargetDish targetDish) {
        Dish dish = dishReader.read(targetDish);
        dishRemover.remove(dish);
    }

    public void updateDish(TargetDish targetDish, DishInfo dishInfo) {
        Dish dish = dishReader.read(targetDish);
        DishStore dishStore = dishStoreReader.read(TargetDishStore.from(dish.getDishStoreId()));
        dishUpdater.update(dishStore, dish, dishInfo);
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
