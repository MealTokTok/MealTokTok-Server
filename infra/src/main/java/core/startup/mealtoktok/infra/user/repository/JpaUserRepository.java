package core.startup.mealtoktok.infra.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import core.startup.mealtoktok.domain.auth.OAuthProvider;
import core.startup.mealtoktok.infra.user.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByOid(String oid);

    boolean existsByProviderAndOid(OAuthProvider provider, String oid);
}
