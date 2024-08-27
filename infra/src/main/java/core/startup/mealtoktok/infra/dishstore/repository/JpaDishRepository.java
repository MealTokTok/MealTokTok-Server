package core.startup.mealtoktok.infra.dishstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import core.startup.mealtoktok.infra.dishstore.entity.DishEntity;

public interface JpaDishRepository extends JpaRepository<DishEntity, Long> {

    @Query(
            "SELECT d FROM DishEntity d "
                    + "WHERE d.dishStoreId = :storeId "
                    + "AND d.dishCategoryId = :categoryId")
    List<DishEntity> findAllByStoreAndCategory(
            @Param("storeId") Long storeId, @Param("categoryId") Long categoryId);

    boolean existsByDishStoreIdAndDishName(Long dishStoreId, String dishName);

    boolean existsByDishStoreIdAndDishNameAndDishIdNot(
            Long dishStoreId, String dishName, Long dishId);

    @Query(
            "SELECT d FROM DishEntity d WHERE d.dishStoreId = :storeId AND d.dishName LIKE %:keyword%")
    List<DishEntity> findByStoreIdAndDishName(
            @Param("storeId") Long storeId, @Param("keyword") String keyword);
}
