package core.startup.mealtoktok.domain.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserCacheManager {

    private final UserCacheRepository userCacheRepository;

    public void cache(User user) {
        userCacheRepository.cache(user);
    }

    public Optional<User> read(TargetUser targetUser) {
        return userCacheRepository.getUser(targetUser);
    }

    public void delete(User user) {
        userCacheRepository.deleteUser(user);
    }

    public void refresh(User user) {
        delete(user);
        cache(user);
    }
}
