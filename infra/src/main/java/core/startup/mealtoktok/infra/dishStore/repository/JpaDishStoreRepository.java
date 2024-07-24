package core.startup.mealtoktok.infra.dishStore.repository;

import core.startup.mealtoktok.infra.dishStore.entity.DishStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishStoreRepository extends JpaRepository<DishStoreEntity, Long> {
}
