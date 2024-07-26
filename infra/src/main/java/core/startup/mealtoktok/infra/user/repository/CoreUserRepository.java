package core.startup.mealtoktok.infra.user.repository;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.auth.OAuthProfile;
import core.startup.mealtoktok.domain.user.UserInfo;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserRepository;
import core.startup.mealtoktok.infra.user.entity.UserEntity;
import core.startup.mealtoktok.infra.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public TargetUser save(OAuthInfo oAuthInfo, String deviceToken, UserInfo userInfo) {
        UserEntity savedUser = jpaUserRepository.save(UserEntity.from(oAuthInfo, deviceToken, userInfo));
        return TargetUser.from(savedUser.getUserId());
    }

    @Override
    public TargetUser save(User user) {
        UserEntity savedUser = jpaUserRepository.save(UserEntity.from(user));
        return TargetUser.from(savedUser.getUserId());
    }

    @Override
    public User findById(TargetUser targetUser) {
        return jpaUserRepository.findById(targetUser.userId())
                .map(UserEntity::toDomain)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public User findByOAuthId(String oid) {
        return jpaUserRepository.findByOid(oid)
                .map(UserEntity::toDomain)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public boolean existsByOAuthInfo(OAuthInfo oAuthInfo) {
        return jpaUserRepository.existsByProviderAndOid(oAuthInfo.provider(), oAuthInfo.oid());
    }
}
