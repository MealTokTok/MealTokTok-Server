package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishAppender {

    private final DishRepository dishRepository;

    public void append(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo) {
        dishRepository.save(dishStore, dishCategory, dishInfo);
    }

}
