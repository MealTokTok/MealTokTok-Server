package core.startup.mealtoktok.infra.dishCategory.repository;

import core.startup.mealtoktok.domain.dishCategory.DishCategory;
import core.startup.mealtoktok.domain.dishCategory.DishCategoryRepository;
import core.startup.mealtoktok.domain.dishCategory.TargetDishCategory;
import core.startup.mealtoktok.infra.dishCategory.entity.DishCategoryEntity;
import core.startup.mealtoktok.infra.dishCategory.exception.DishCategoryNotFoundException;
import core.startup.mealtoktok.infra.dishStore.exception.DishStoreNotFoundException;
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
