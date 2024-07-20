package core.startup.mealtoktok.domain.user;

import java.util.Optional;

public interface TokenRepository {

    void setRefreshToken(RefreshToken refreshToken);

    Optional<RefreshToken> getRefreshToken(TargetUser targetUser);

    void deleteRefreshToken(TargetUser targetUser);

    void ban(String token);

    boolean isAlreadyLogin(String token);


}
