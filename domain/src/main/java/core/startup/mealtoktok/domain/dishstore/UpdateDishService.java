package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateDishService {

    private final DishReader dishReader;
    private final DishStoreReader dishStoreReader;
    private final DishUpdater dishUpdater;

    public void updateDish(TargetDish targetDish, DishInfo dishInfo) {
        Dish dish = dishReader.read(targetDish);
        DishStore dishStore = dishStoreReader.read(TargetDishStore.from(dish.getDishStoreId()));
        dishUpdater.update(dishStore, dish, dishInfo);
    }
}
