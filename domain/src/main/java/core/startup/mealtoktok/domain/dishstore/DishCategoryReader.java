package core.startup.mealtoktok.domain.dishstore;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishCategoryReader {

    private final DishRepository dishRepository;

    public DishCategory read(TargetDishCategory targetDishCategory) {
        return dishRepository.findDishCategoryById(targetDishCategory);
    }

    public List<DishCategory> readAll() {
        return dishRepository.findAllCategories();
    }
}
