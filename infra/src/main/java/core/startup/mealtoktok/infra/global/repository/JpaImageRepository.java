package core.startup.mealtoktok.infra.global.repository;

import core.startup.mealtoktok.infra.jpa.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaImageRepository extends JpaRepository<ImageEntity, Long> {

}
