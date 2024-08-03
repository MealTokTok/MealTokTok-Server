package core.startup.mealtoktok.domain.auth;

import java.util.Optional;

import core.startup.mealtoktok.domain.user.TargetUser;

public interface TokenRepository {

    void setRefreshToken(RefreshToken refreshToken);

    Optional<RefreshToken> getRefreshToken(TargetUser targetUser);

    void ban(String token);

    boolean isAlreadyLogin(String token);
}
