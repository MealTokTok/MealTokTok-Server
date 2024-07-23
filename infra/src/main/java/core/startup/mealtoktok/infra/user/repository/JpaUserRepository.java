package core.startup.mealtoktok.infra.user.repository;

import core.startup.mealtoktok.domain.auth.OAuthProvider;
import core.startup.mealtoktok.infra.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {



    Optional<UserEntity> findByOid(String oid);

    boolean existsByProviderAndOid(OAuthProvider provider, String oid);
}
