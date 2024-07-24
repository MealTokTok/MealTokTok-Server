package core.startup.mealtoktok.infra.dish.repository;

import core.startup.mealtoktok.infra.dish.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishRepository extends JpaRepository<DishEntity, Long> {
}
