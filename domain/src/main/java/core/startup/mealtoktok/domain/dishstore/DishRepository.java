package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.common.dto.Image;
import java.util.List;

public interface DishRepository {

    void saveDish(DishStore dishStore, DishCategory dishCategory, List<Image> images,  DishInfo dishInfo);

    Dish findDishById(TargetDish targetDish);

    void deleteDish(Dish dish, List<DishImage> dishImages);

    List<Dish> findAllByStoreAndCategory(DishStore dishStore, DishCategory dishCategory);

    void updateDish(Dish dish, DishInfo dishInfo);

    boolean existsByDishStoreIdAndDishName(DishStore dishStore, String dishName);

    boolean existsByNameExcludingTargetDish(DishStore dishStore, Dish dish, String dishName);

    DishCategory findDishCategoryById(TargetDishCategory targetDishCategory);

    void saveDishCategory(DishCategoryInfo dishCategoryInfo);

    boolean existsByDishCategoryName(String dishCategoryName);

    boolean existsByNameExcludingTargetCategory(DishCategory dishCategory, String dishCategoryName);

    void updateDishCategory(DishCategory dishCategory, DishCategoryInfo dishCategoryInfo);

    void deleteDishCategory(DishCategory dishCategory);

    List<DishCategory> findAllCategories();

    List<Dish> findAllByStoreAndKeyword(DishStore dishStore, String keyword);

    List<DishImage> findAllDishImageByDishId(TargetDish targetDish);

    void saveDishImages(Dish dish, List<Image> images);

    void deleteDishImages(List<DishImage> dishImages);
}
