package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReadDishService {

    private final DishStoreReader dishStoreReader;
    private final DishCategoryReader dishCategoryReader;
    private final DishReader dishReader;

    public List<Dish> readDishes(
            TargetDishStore targetDishStore, TargetDishCategory targetDishCategory) {
        DishStore dishStore = dishStoreReader.read(targetDishStore);
        DishCategory dishCategory = dishCategoryReader.read(targetDishCategory);
        return dishReader.readAll(dishStore, dishCategory);
    }
}
