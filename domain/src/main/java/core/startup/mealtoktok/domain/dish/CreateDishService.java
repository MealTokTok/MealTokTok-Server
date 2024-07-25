package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.DishStore.DishStoreReader;
import core.startup.mealtoktok.domain.DishStore.TargetDishStore;
import core.startup.mealtoktok.domain.dishCategory.DishCategory;
import core.startup.mealtoktok.domain.dishCategory.DishCategoryReader;
import core.startup.mealtoktok.domain.dishCategory.TargetDishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateDishService {

    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final DishAppender dishAppender;

    public void createDish(TargetDishStore targetDishStore,
                           TargetDishCategory targetDishCategory,
                           DishInfo dishInfo){
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);

        dishAppender.append(dishStore, dishCategory, dishInfo);
    }

}
