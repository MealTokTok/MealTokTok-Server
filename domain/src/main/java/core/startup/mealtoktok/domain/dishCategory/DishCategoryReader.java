package core.startup.mealtoktok.domain.dishCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishCategoryReader {

    private final DishCategoryRepository dishCategoryRepository;

    public DishCategory read(TargetDishCategory targetDishCategory){
        return dishCategoryRepository.findById(targetDishCategory);
    }
}
