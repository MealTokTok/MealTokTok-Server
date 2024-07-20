package core.startup.mealtoktok.infra.user.repository;

import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.domain.user.User;
import core.startup.mealtoktok.domain.user.UserRepository;
import core.startup.mealtoktok.infra.user.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User findById(TargetUser targetUser) {
        return jpaUserRepository.findById(targetUser.userId())
                .map(UserEntity::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public void updateAddressInfo(User user) {
        UserEntity userEntity = jpaUserRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userEntity.updateAddressInfo(user.getAddressInfo());
    }
}
