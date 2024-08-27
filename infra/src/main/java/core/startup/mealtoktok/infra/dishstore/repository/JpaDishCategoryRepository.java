package core.startup.mealtoktok.infra.dishstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.dishstore.entity.DishCategoryEntity;

public interface JpaDishCategoryRepository extends JpaRepository<DishCategoryEntity, Long> {

    boolean existsByCategoryName(String dishCategoryName);

    boolean existsByCategoryNameAndCategoryIdNot(String dishCategoryName, Long categoryId);
}
