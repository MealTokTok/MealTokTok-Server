package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteDishService {

    private final DishReader dishReader;
    private final DishRemover dishRemover;

    public void deleteDish(TargetDish targetDish) {
        Dish dish = dishReader.read(targetDish);
        dishRemover.remove(dish);
    }
}
