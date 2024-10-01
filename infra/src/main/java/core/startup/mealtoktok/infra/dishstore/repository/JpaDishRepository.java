package core.startup.mealtoktok.infra.dishstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.dishstore.entity.DishEntity;

public interface JpaDishRepository extends JpaRepository<DishEntity, Long> {

    boolean existsByDishStoreIdAndDishNameAndDishIdNot(
            Long dishStoreId, String dishName, Long dishId);

    List<DishEntity> findByIsDeletedFalseAndDishNameContaining(String keyword);

    Optional<DishEntity> findByDishIdAndIsDeletedFalse(Long aLong);

    Optional<DishEntity> findByDishCategoryIdAndIsDeletedFalse(Long categoryId);

    boolean existsByDishStoreIdAndDishNameAndIsDeletedFalse(Long storeId, String dishName);

    List<DishEntity> findAllByDishIdInAndIsDeletedFalse(List<Long> dishIds);
    ;
}
