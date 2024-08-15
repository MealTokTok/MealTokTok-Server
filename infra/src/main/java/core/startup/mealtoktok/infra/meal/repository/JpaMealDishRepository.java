package core.startup.mealtoktok.infra.meal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.meal.entity.MealDishEntity;

public interface JpaMealDishRepository extends JpaRepository<MealDishEntity, Long> {

    List<MealDishEntity> getReferenceByMealId(Long mealId);

    List<MealDishEntity> findAllByMealId(Long mealId);
}
