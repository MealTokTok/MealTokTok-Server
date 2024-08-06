package core.startup.mealtoktok.domain.dishstore;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DishCategoryRemover {

    private final DishRepository dishRepository;

    public void remove(DishCategory dishCategory) {
        dishRepository.deleteDishCategory(dishCategory);
    }
}
