package core.startup.mealtoktok.infra.dishstore.repository;

import core.startup.mealtoktok.infra.dishstore.entity.DishImageEntity;
import core.startup.mealtoktok.infra.dishstore.entity.DishImageId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaDishImageRepository extends JpaRepository<DishImageEntity, DishImageId> {


    @Query("SELECT d FROM DishImageEntity d WHERE d.dishImageId.dishId = :dishId")
    List<DishImageEntity> findAllByDishId(@Param("dishId") Long dishId);

    @Modifying
    @Query("DELETE FROM DishImageEntity d WHERE d.dishImageId.dishId = :dishId")
    void deleteByDishId(@Param("dishId") Long dishId);

}
