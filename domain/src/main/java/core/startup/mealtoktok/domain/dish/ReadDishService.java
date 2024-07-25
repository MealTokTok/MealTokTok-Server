package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.DishStore.DishStoreReader;
import core.startup.mealtoktok.domain.DishStore.TargetDishStore;
import core.startup.mealtoktok.domain.dishCategory.DishCategory;
import core.startup.mealtoktok.domain.dishCategory.DishCategoryReader;
import core.startup.mealtoktok.domain.dishCategory.TargetDishCategory;
import core.startup.mealtoktok.domain.user.TargetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReadDishService {

    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final DishReader dishReader;

    public List<Dish> readDishes(TargetUser targetUser, TargetDishStore targetDishStore, TargetDishCategory targetDishCategory){
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        return dishReader.readAll(dishStore, dishCategory);
    }
}
