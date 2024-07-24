package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.dishCategory.DishCategory;

public interface DishRepository {

    void save(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo);
}
