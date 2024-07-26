package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateDishService {

    private final DishReader dishReader;
    private final DishStoreReader dishStoreReader;
    private final DishUpdater dishUpdater;
    private final DishValidator dishValidator;

    public void updateDish(TargetDish targetDish, DishInfo dishInfo) {
        Dish dish = dishReader.read(targetDish);
        DishStore dishStore = dishStoreReader.read(TargetDishStore.from(dish.getDishStoreId()));
        dishValidator.validateName(dishStore, dishInfo.dishName());
        dishUpdater.update(dish, dishInfo);
    }
}
