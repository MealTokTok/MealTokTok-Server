package core.startup.mealtoktok.domain.dish;

import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateDishService {

    private final UserReader userReader;
    private final DishReader dishReader;
    private final DishUpdater dishUpdater;

    public void updateDish(TargetUser targetUser, TargetDish targetDish, DishInfo dishInfo) {
        User user = userReader.read(targetUser);
        //검증 로직 추가
        Dish dish = dishReader.read(targetDish);
        dishUpdater.update(dish, dishInfo);
    }
}
