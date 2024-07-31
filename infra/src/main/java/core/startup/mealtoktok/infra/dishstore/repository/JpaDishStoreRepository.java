package core.startup.mealtoktok.infra.dishstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.auth.entity.DishStoreEntity;

public interface JpaDishStoreRepository extends JpaRepository<DishStoreEntity, Long> {}
