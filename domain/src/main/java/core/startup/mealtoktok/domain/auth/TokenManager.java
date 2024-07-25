package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.user.TargetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final TokenRepository tokenRepository;

    public void saveRefreshToken(RefreshToken refreshToken) {
        tokenRepository.setRefreshToken(refreshToken);
    }

    public Optional<String> getRefreshToken(TargetUser targetUser) {
        return tokenRepository
                .getRefreshToken(targetUser)
                .map(RefreshToken::refreshToken);
    }

    public boolean isAlreadyLogin(String accessToken) {
        return tokenRepository.isAlreadyLogin(accessToken);
    }

}
