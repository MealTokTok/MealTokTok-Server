package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishCategoryReader {

    private final DishRepository dishRepository;

    public DishCategory read(TargetDishCategory targetDishCategory){
        return dishRepository.findCategoryById(targetDishCategory);
    }
}
