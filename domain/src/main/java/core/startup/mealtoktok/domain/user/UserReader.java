package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;
    private final UserCacheManager userCacheManager;

    public User read(TargetUser targetUser) {
        return userCacheManager.read(targetUser).orElseGet( () ->{
            User user = userRepository.findById(targetUser);
            userCacheManager.cache(user);
            return user;
        });
    }

    public boolean isAlreadyRegistered(OAuthInfo oAuthInfo) {
        return userRepository.existsByOAuthInfo(oAuthInfo);
    }
}
