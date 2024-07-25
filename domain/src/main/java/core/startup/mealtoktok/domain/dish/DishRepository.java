package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.dishCategory.DishCategory;

import java.util.List;

public interface DishRepository {

    void save(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo);

    Dish findById(TargetDish targetDish);

    void delete(Dish dish);

    List<Dish> findAllByStoreAndCategory(DishStore dishStore, DishCategory dishCategory);

    void update(Dish dish, DishInfo dishInfo);

    boolean existsByDishStoreEntityAndDishName(DishStore dishStore, String dishName);
}
