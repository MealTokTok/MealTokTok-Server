package core.startup.mealtoktok.domain.dishstore;

import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReadDishService {

    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final DishReader dishReader;
    private final UserReader userReader;

    public List<Dish> readDishes(TargetUser targetUser, TargetDishStore targetDishStore, TargetDishCategory targetDishCategory){
        //User user = userReader.read(targetUser);
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        return dishReader.readAll(dishStore, dishCategory);
    }
}
