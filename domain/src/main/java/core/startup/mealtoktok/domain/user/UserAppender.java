package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {

    private final UserRepository userRepository;

    public void append(OAuthInfo oAuthInfo, UserInfo userInfo) {
        userRepository.save(oAuthInfo, userInfo);
    }

}
