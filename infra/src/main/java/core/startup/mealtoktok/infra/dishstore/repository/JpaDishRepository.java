package core.startup.mealtoktok.infra.dishstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.dishstore.entity.DishEntity;

public interface JpaDishRepository extends JpaRepository<DishEntity, Long> {

    List<DishEntity> findByDishCategoryId(Long categoryId);

    boolean existsByDishStoreIdAndDishName(Long dishStoreId, String dishName);

    boolean existsByDishStoreIdAndDishNameAndDishIdNot(
            Long dishStoreId, String dishName, Long dishId);

    List<DishEntity> findByDishNameContaining(String keyword);
    ;
}
