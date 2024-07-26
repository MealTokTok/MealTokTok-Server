package core.startup.mealtoktok.infra.dishstore.repository;

import core.startup.mealtoktok.infra.auth.entity.DishCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishCategoryRepository extends JpaRepository<DishCategoryEntity, Long> {
}
