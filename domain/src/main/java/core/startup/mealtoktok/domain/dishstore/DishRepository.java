package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

public interface DishRepository {

    void saveDishCategory(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo);

    Dish findDishById(TargetDish targetDish);

    void deleteDishCategory(Dish dish);

    List<Dish> findAllByStoreAndCategory(DishStore dishStore, DishCategory dishCategory);

    void updateDishCategory(Dish dish, DishInfo dishInfo);

    boolean existsByDishStoreIdAndDishName(DishStore dishStore, String dishName);

    boolean existsByNameExcludingTargetDish(DishStore dishStore, Dish dish, String dishName);

    DishCategory findDishById(TargetDishCategory targetDishCategory);

    void saveDishCategory(DishCategoryInfo dishCategoryInfo);

    boolean existsByDishCategoryName(String dishCategoryName);

    boolean existsByNameExcludingTargetCategory(DishCategory dishCategory, String dishCategoryName);

    void updateDishCategory(DishCategory dishCategory, DishCategoryInfo dishCategoryInfo);

    void deleteDishCategory(DishCategory dishCategory);

    List<DishCategory> findAllCategories();

    boolean existsById(Long dishId);
}
