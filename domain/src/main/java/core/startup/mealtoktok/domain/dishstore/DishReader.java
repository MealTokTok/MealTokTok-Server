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

    public List<Dish> readAll(DishCategory dishCategory) {
        return dishRepository.findAllByCategory(dishCategory);
    }

    public List<Dish> search(String keyword) {
        return dishRepository.findAllByKeyword(keyword);
    }
}
