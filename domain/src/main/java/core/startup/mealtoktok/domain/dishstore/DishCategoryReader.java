package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishCategoryReader {

    private final DishRepository dishRepository;

    public DishCategory read(TargetDishCategory targetDishCategory) {
        return dishRepository.findCategoryById(targetDishCategory);
    }
}
