package core.startup.mealtoktok.domain.auth;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.auth.exception.AlreadyRegisteredUserException;
import core.startup.mealtoktok.domain.user.UserRepository;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public boolean isAlreadyRegistered(OAuthInfo oAuthInfo) {
        return userRepository.existsByOAuthInfo(oAuthInfo);
    }

    public void validate(OAuthInfo oAuthInfo) {
        if (isAlreadyRegistered(oAuthInfo)) {
            throw AlreadyRegisteredUserException.EXCEPTION;
        }
    }
}
