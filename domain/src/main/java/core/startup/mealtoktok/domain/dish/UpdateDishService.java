package core.startup.mealtoktok.domain.dish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateDishService {

    private final DishReader dishReader;
    private final DishUpdater dishUpdater;

    public void updateDish(TargetDish targetDish, DishInfo dishInfo) {
        Dish dish = dishReader.read(targetDish);
        dishUpdater.update(dish, dishInfo);
    }
}
