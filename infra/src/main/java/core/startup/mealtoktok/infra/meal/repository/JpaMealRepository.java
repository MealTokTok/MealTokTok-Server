package core.startup.mealtoktok.infra.meal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.meal.entity.MealEntity;
import core.startup.mealtoktok.infra.meal.entity.MealOwnerVO;

public interface JpaMealRepository extends JpaRepository<MealEntity, Long> {

    boolean existsByMealName(String mealName);

    boolean existsByMealNameAndMealIdNot(String mealName, Long melaId);

    List<MealEntity> findAllByMealOwner(MealOwnerVO mealOwnerVO);
}
