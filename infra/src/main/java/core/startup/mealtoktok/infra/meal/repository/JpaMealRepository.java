package core.startup.mealtoktok.infra.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.meal.entity.MealEntity;

public interface JpaMealRepository extends JpaRepository<MealEntity, Long> {

    boolean existsByMealName(String mealName);
}
