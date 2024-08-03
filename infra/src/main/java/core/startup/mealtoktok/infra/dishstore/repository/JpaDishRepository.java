package core.startup.mealtoktok.infra.dishstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import core.startup.mealtoktok.infra.dishstore.entity.DishEntity;
import core.startup.mealtoktok.infra.dishstore.entity.DishStoreEntity;

public interface JpaDishRepository extends JpaRepository<DishEntity, Long> {

    @Query(
            "SELECT d FROM DishEntity d "
                    + "WHERE d.dishStoreEntity.storeId = :storeId "
                    + "AND d.dishCategoryEntity.categoryId = :categoryId")
    List<DishEntity> findAllByStoreAndCategory(
            @Param("storeId") Long storeId, @Param("categoryId") Long categoryId);

    boolean existsByDishStoreEntityAndDishName(DishStoreEntity dishStoreEntity, String dishName);
}
