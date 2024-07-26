package core.startup.mealtoktok.infra.dish.repository;

import core.startup.mealtoktok.domain.dish.DishCategory;
import core.startup.mealtoktok.domain.dish.DishCategoryRepository;
import core.startup.mealtoktok.domain.dish.TargetDishCategory;
import core.startup.mealtoktok.infra.dish.entity.DishCategoryEntity;
import core.startup.mealtoktok.infra.dish.exception.DishCategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreDishCategoryRepository implements DishCategoryRepository {

    private final JpaDishCategoryRepository jpaDishCategoryRepository;

    @Override
    public DishCategory findById(TargetDishCategory targetCategory) {
        return jpaDishCategoryRepository.findById(targetCategory.categoryId())
                .map(DishCategoryEntity::toDomain)
                .orElseThrow(() -> DishCategoryNotFoundException.EXCEPTION);
    }
}
