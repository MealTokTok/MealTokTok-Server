package core.startup.mealtoktok.infra.dishstore.repository;

import core.startup.mealtoktok.infra.auth.entity.DishStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishStoreRepository extends JpaRepository<DishStoreEntity, Long> {
}
