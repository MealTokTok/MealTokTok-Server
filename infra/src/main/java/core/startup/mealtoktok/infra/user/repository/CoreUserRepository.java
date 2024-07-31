package core.startup.mealtoktok.infra.user.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.user.DeliveryAddress;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserProfile;
import core.startup.mealtoktok.domain.user.UserRepository;
import core.startup.mealtoktok.infra.user.entity.UserEntity;
import core.startup.mealtoktok.infra.user.exception.UserNotFoundException;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public TargetUser save(
            OAuthInfo oAuthInfo,
            String deviceToken,
            UserProfile userProfile,
            DeliveryAddress deliveryAddress) {
        UserEntity savedUser =
                jpaUserRepository.save(
                        UserEntity.from(oAuthInfo, deviceToken, userProfile, deliveryAddress));
        return TargetUser.from(savedUser.getUserId());
    }

    @Override
    public TargetUser save(User user) {
        UserEntity savedUser = jpaUserRepository.save(UserEntity.from(user));
        return TargetUser.from(savedUser.getUserId());
    }

    @Override
    public User findById(TargetUser targetUser) {
        return jpaUserRepository
                .findById(targetUser.userId())
                .map(UserEntity::toDomain)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public User findByOAuthId(String oid) {
        return jpaUserRepository
                .findByOid(oid)
                .map(UserEntity::toDomain)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public boolean existsByOAuthInfo(OAuthInfo oAuthInfo) {
        return jpaUserRepository.existsByProviderAndOid(oAuthInfo.provider(), oAuthInfo.oid());
    }
}
