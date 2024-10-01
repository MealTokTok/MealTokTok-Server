package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import core.startup.mealtoktok.common.dto.Image;

public interface DishRepository {

    void saveDish(
            DishStore dishStore, DishCategory dishCategory, DishContent dishContent, Image image);

    Dish findDishById(TargetDish targetDish);

    void deleteDish(Dish dish);

    List<Dish> findAllByCategory(DishCategory dishCategory);

    void updateDish(Dish dish, DishContent dishContent, Image image);

    boolean existsByDishStoreIdAndDishName(DishStore dishStore, String dishName);

    boolean existsByNameExcludingTargetDish(DishStore dishStore, Dish dish, String dishName);

    DishCategory findDishCategoryById(TargetDishCategory targetDishCategory);

    void saveDishCategory(DishCategoryInfo dishCategoryInfo);

    boolean existsByDishCategoryName(String dishCategoryName);

    boolean existsByNameExcludingTargetCategory(DishCategory dishCategory, String dishCategoryName);

    void updateDishCategory(DishCategory dishCategory, DishCategoryInfo dishCategoryInfo);

    void deleteDishCategory(DishCategory dishCategory);

    List<DishCategory> findAllCategories();

    List<Dish> findAllByKeyword(String keyword);

    Dish findActiveDishById(TargetDish targetDish);

    void findAllActiveDishById(List<Long> dishIds);
}
