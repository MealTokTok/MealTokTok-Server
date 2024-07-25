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
    private final UserReader userReader;

    public void deleteDish(TargetUser targetUser, TargetDish targetDish) {
       //User user = userReader.read(targetUser);
        Dish dish = dishReader.read(targetDish);
        //검증 로직
        dishRemover.remove(dish);
    }
}
