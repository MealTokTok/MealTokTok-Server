package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;

public interface UserRepository {

    User findById(TargetUser targetUser);

    User findByEmail(String email);

    void save(OAuthInfo oAuthInfo, UserInfo userInfo);

    User update(User user);

    boolean existsByOAuthInfo(OAuthInfo oAuthInfo);
}
