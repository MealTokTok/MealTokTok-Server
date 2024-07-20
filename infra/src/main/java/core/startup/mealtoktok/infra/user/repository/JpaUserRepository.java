package core.startup.mealtoktok.infra.user.repository;

import core.startup.mealtoktok.infra.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
