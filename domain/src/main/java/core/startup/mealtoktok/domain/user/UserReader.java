package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;
    private final UserCacheManager userCacheManager;

    public User read(TargetUser targetUser) {
        return userCacheManager
                .read(targetUser)
                .orElseGet(
                        () -> {
                            User user = userRepository.findById(targetUser);
                            userCacheManager.cache(user);
                            return user;
                        });
    }

    public User read(String oid) {
        return userRepository.findByOAuthId(oid);
    }
}
