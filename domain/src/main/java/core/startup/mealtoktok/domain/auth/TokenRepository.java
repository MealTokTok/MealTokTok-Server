package core.startup.mealtoktok.domain.auth;

import java.util.Optional;

import core.startup.mealtoktok.domain.user.UserId;

public interface TokenRepository {

    void setRefreshToken(RefreshToken refreshToken);

    Optional<RefreshToken> getRefreshToken(UserId userId);

    void ban(String token);

    boolean isAlreadyLogin(String token);
}
