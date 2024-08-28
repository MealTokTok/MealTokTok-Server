package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.auth.OAuthInfo;

@Component
@RequiredArgsConstructor
public class UserAppender {

    private final UserRepository userRepository;

    public UserId append(OAuthInfo oAuthInfo, String deviceToken, UserProfile userProfile) {
        return userRepository.save(oAuthInfo, deviceToken, userProfile);
    }
}
