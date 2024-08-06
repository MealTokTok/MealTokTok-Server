package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishReader {

    private final DishRepository dishRepository;

    public Dish read(TargetDish targetDish) {
        return dishRepository.findDishById(targetDish);
    }

    public List<Dish> readAll(DishStore dishStore, DishCategory dishCategory) {
        return dishRepository.findAllByStoreAndCategory(dishStore, dishCategory);
    }
}
