package core.startup.mealtoktok.infra.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.infra.jpa.entity.ImageEntity;

public interface JpaImageRepository extends JpaRepository<ImageEntity, Long> {}
