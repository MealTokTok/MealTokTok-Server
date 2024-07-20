package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.common.exception.ErrorCode;
import core.startup.mealtoktok.domain.global.exception.DomainException;
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

    public void deleteRefreshToken(TargetUser targetUser) {
        tokenRepository.deleteRefreshToken(targetUser);
    }

    public void updateRefreshToken(RefreshToken refreshToken) {
        deleteRefreshToken(TargetUser.from(refreshToken.userId()));
        saveRefreshToken(refreshToken);
    }

    public void ban(String accessToken) {
        tokenRepository.ban(accessToken);
    }

    public boolean isAlreadyLogin(String accessToken) {
        return tokenRepository.isAlreadyLogin(accessToken);
    }

    public void validate(TargetUser targetUser, String givenRefreshToken) {
        getRefreshToken(targetUser)
                .filter(existRefreshToken -> existRefreshToken.equals(givenRefreshToken))
                .orElseThrow(() -> new DomainException(ErrorCode.INVALID_TOKEN));
    }



}
