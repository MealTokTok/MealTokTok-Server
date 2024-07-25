package core.startup.mealtoktok.infra.dishCategory.repository;

import core.startup.mealtoktok.infra.dishCategory.entity.DishCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishCategoryRepository extends JpaRepository<DishCategoryEntity, Long> {
}
