package core.startup.mealtoktok.infra.meal.repository;

import core.startup.mealtoktok.domain.meal.Meal;
import java.util.List;

import java.util.Optional;
import org.geolatte.geom.M;
import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.meal.entity.MealEntity;
import core.startup.mealtoktok.infra.meal.entity.MealOwnerVO;

public interface JpaMealRepository extends JpaRepository<MealEntity, Long> {

    boolean existsByMealNameAndMealIdNot(String mealName, Long mealId);

    List<MealEntity> findAllByMealOwner(MealOwnerVO mealOwnerVO);

    Optional<MealEntity> findByMealIdAndIsDeletedFalse(Long mealId);

    boolean existsByMealNameAndIsDeletedFalse(String mealName);
}
