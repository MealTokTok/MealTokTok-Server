package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.DishStore.DishStore;
import core.startup.mealtoktok.domain.DishStore.DishStoreReader;
import core.startup.mealtoktok.domain.DishStore.TargetDishStore;
import core.startup.mealtoktok.domain.dishCategory.DishCategory;
import core.startup.mealtoktok.domain.dishCategory.DishCategoryReader;
import core.startup.mealtoktok.domain.dishCategory.TargetDishCategory;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDishService {

    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final DishAppender dishAppender;
    private final UserReader userReader;

    public void createDish(//TargetUser targetUser,
                           TargetDishStore targetDishStore,
                           TargetDishCategory targetDishCategory,
                           DishInfo dishInfo){
        //User user = userReader.read(targetUser);
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        dishAppender.append(dishStore, dishCategory, dishInfo);
    }

}
