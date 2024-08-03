package core.startup.mealtoktok.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.auth.exception.AlreadyRegisteredUserException;
import core.startup.mealtoktok.domain.user.exception.RemovedUserException;

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

    public void validate(User user) {
        if (user.isRemoved()) {
            throw RemovedUserException.EXCEPTION;
        }
    }
}
