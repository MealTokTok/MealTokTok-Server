package core.startup.mealtoktok.infra.dish.repository;

import core.startup.mealtoktok.infra.dish.entity.DishCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishCategoryRepository extends JpaRepository<DishCategoryEntity, Long> {
}
