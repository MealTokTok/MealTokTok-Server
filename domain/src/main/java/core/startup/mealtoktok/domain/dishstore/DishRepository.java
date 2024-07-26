package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

public interface DishRepository {

    void save(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo);

    Dish findCategoryById(TargetDish targetDish);

    void delete(Dish dish);

    List<Dish> findAllByStoreAndCategory(DishStore dishStore, DishCategory dishCategory);

    void update(Dish dish, DishInfo dishInfo);

    boolean existsByDishStoreEntityAndDishName(DishStore dishStore, String dishName);

    DishCategory findCategoryById(TargetDishCategory targetDishCategory);
}
