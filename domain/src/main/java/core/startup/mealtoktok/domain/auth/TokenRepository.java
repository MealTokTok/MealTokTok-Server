package core.startup.mealtoktok.domain.auth;

import core.startup.mealtoktok.domain.user.TargetUser;

import java.util.Optional;

public interface TokenRepository {

    void setRefreshToken(RefreshToken refreshToken);

    Optional<RefreshToken> getRefreshToken(TargetUser targetUser);

    void ban(String token);

    boolean isAlreadyLogin(String token);


}
