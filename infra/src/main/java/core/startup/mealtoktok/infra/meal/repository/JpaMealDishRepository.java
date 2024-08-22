package core.startup.mealtoktok.infra.meal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import core.startup.mealtoktok.infra.meal.entity.MealDishEntity;

public interface JpaMealDishRepository extends JpaRepository<MealDishEntity, Long> {

    List<MealDishEntity> findAllByMealId(Long mealId);

    @Modifying
    @Query("UPDATE MealDishEntity m SET m.dishId = :dishId WHERE m.mealDishId = :mealDishId")
    void updateDishId(@Param("mealDishId") Long mealDishId, @Param("dishId") Long dishId);
}
