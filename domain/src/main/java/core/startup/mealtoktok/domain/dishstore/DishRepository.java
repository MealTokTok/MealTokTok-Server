package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

public interface DishRepository {

    void saveDishCategory(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo);

    Dish findDishById(TargetDish targetDish);

    void deleteDishCategory(Dish dish);

    List<Dish> findAllByStoreAndCategory(DishStore dishStore, DishCategory dishCategory);

    void updateDishCategory(Dish dish, DishInfo dishInfo);

    boolean existsByDishStoreEntityAndDishName(DishStore dishStore, String dishName);

    DishCategory findDishById(TargetDishCategory targetDishCategory);

    void saveDishCategory(DishCategoryInfo dishCategoryInfo);

    boolean existsByDishCategoryName(String dishCategoryName);

    void updateDishCategory(DishCategory dishCategory, DishCategoryInfo dishCategoryInfo);

    void deleteDishCategory(DishCategory dishCategory);

    List<DishCategory> findAllCategories();
}
