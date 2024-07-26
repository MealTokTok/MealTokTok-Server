package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public TargetUser update(User user, OAuthProfile oAuthProfile, String deviceToken) {
        user.update(oAuthProfile, deviceToken);
        return userRepository.save(user);
    }
}
