package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishAppender {

    private final DishValidator dishValidator;
    private final DishRepository dishRepository;

    public void append(DishStore dishStore, DishCategory dishCategory, DishInfo dishInfo) {
        dishValidator.validateName(dishStore, dishInfo.dishName());
        dishRepository.save(dishStore, dishCategory, dishInfo);
    }
}
