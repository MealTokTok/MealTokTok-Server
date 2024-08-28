package core.startup.mealtoktok.infra.user.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserId;
import core.startup.mealtoktok.domain.user.UserProfile;
import core.startup.mealtoktok.domain.user.UserRepository;
import core.startup.mealtoktok.domain.user.WithDrawReason;
import core.startup.mealtoktok.infra.user.entity.UserEntity;
import core.startup.mealtoktok.infra.user.entity.WithDrawReasonEntity;
import core.startup.mealtoktok.infra.user.exception.UserNotFoundException;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreUserRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final WithDrawReasonJpaRepository withDrawReasonJpaRepository;

    @Override
    public UserId save(OAuthInfo oAuthInfo, String deviceToken, UserProfile userProfile) {
        UserEntity savedUser =
                userJpaRepository.save(UserEntity.of(oAuthInfo, deviceToken, userProfile));
        return UserId.from(savedUser.getUserId());
    }

    @Override
    public UserId update(User user) {
        UserEntity userEntity =
                userJpaRepository
                        .findById(user.getUserId().getValue())
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        userEntity.update(user).toDomain();
        UserEntity saved = userJpaRepository.save(userEntity);
        return UserId.from(saved.getUserId());
    }

    @Override
    public User findById(UserId userId) {
        return userJpaRepository
                .findById(userId.getValue())
                .map(UserEntity::toDomain)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public User findByOAuthId(String oid) {
        return userJpaRepository
                .findByOid(oid)
                .map(UserEntity::toDomain)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public boolean existsByOAuthInfo(OAuthInfo oAuthInfo) {
        return userJpaRepository.existsByProviderAndOid(oAuthInfo.provider(), oAuthInfo.oid());
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userJpaRepository.existsByUserProfileNickname(nickname);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(UserEntity.from(user));
    }

    @Override
    public void deleteWithReason(User user, WithDrawReason withDrawReason) {
        delete(user);
        withDrawReasonJpaRepository.save(WithDrawReasonEntity.from(withDrawReason));
    }
}
