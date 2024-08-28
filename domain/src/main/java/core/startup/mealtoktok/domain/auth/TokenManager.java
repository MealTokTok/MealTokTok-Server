package core.startup.mealtoktok.domain.auth;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.domain.user.UserId;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final TokenRepository tokenRepository;

    public void saveRefreshToken(RefreshToken refreshToken) {
        tokenRepository.setRefreshToken(refreshToken);
    }

    public Optional<String> getRefreshToken(UserId userId) {
        return tokenRepository.getRefreshToken(userId).map(RefreshToken::refreshToken);
    }

    public boolean isAlreadyLogin(String accessToken) {
        return tokenRepository.isAlreadyLogin(accessToken);
    }
}
