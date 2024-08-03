package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishAppender {

    private final DishRepository dishRepository;

    public void append(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo) {
        dishRepository.save(dishStore, dishCategory, dishInfo);
    }
}
