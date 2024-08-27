package core.startup.mealtoktok.infra.meal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.domain.meal.MealOwner;
import core.startup.mealtoktok.infra.meal.entity.MealEntity;

public interface JpaMealRepository extends JpaRepository<MealEntity, Long> {

    boolean existsByMealName(String mealName);

    boolean existsByMealNameAndMealIdNot(String mealName, Long melaId);

    List<MealEntity> findAllByMealOwner(MealOwner mealOwner);
}
