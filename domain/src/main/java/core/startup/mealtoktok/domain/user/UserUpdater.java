package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public TargetUser update(OAuthProfile oAuthProfile) {
        return userRepository.update(oAuthProfile);
    }
}
