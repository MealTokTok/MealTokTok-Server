package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.DishStore.DishStoreReader;
import core.startup.mealtoktok.domain.DishStore.TargetDishStore;
import core.startup.mealtoktok.domain.dishCategory.DishCategory;
import core.startup.mealtoktok.domain.dishCategory.DishCategoryReader;
import core.startup.mealtoktok.domain.dishCategory.TargetDishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDishService {

    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final DishAppender dishAppender;

    public void createDish(TargetDishStore targetStore,
                           TargetDishCategory targetDishCategory,
                           DishInfo dishInfo){
        DishStore dishStore = dishStoreReader.read(targetStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        dishAppender.append(dishStore, dishCategory, dishInfo);
    }

}
