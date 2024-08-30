package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishImageReader {

    private final DishRepository dishRepository;

    public List<DishImage> readAll(TargetDish targetDish) {
        return dishRepository.findAllDishImageByDishId(targetDish);
    }

    public DishImage read(TargetDish targetDish) {
        return dishRepository.findDishImageByDishId(targetDish);
    }
}
