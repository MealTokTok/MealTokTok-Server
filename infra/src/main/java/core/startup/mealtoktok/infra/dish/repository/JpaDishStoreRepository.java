package core.startup.mealtoktok.infra.dish.repository;

import core.startup.mealtoktok.infra.dish.entity.DishStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishStoreRepository extends JpaRepository<DishStoreEntity, Long> {
}
