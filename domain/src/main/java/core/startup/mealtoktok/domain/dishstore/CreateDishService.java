package core.startup.mealtoktok.domain.dishstore;

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
    private final DishValidator dishValidator;

    public void createDish(TargetDishStore targetDishStore,
                           TargetDishCategory targetDishCategory,
                           DishInfo dishInfo){
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        dishValidator.validateName(dishStore, dishInfo.dishName());
        dishAppender.append(dishStore, dishCategory, dishInfo);
    }

}
