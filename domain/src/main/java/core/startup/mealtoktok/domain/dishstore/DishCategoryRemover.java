package core.startup.mealtoktok.domain.dishstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishCategoryRemover {

    private final DishRepository dishRepository;

    public void remove(DishCategory dishCategory) {
        dishRepository.deleteDishCategory(dishCategory);
    }
}
