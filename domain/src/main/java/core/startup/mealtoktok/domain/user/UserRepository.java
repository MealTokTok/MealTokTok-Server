package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.auth.OAuthProfile;

public interface UserRepository {

    User findById(TargetUser targetUser);

    User findByOAuthId(String email);

    TargetUser save(OAuthInfo oAuthInfo, UserInfo userInfo);

    TargetUser update(OAuthProfile oAuthProfile);

    boolean existsByOAuthInfo(OAuthInfo oAuthInfo);
}
