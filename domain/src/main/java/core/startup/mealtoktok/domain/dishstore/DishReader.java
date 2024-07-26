package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DishReader {

    private final DishRepository dishRepository;

    public Dish read(TargetDish targetDish){
        return dishRepository.findCategoryById(targetDish);
    }

    public List<Dish> readAll(DishStore dishStore, DishCategory dishCategory) {
        return dishRepository.findAllByStoreAndCategory(dishStore, dishCategory);
    }
}
