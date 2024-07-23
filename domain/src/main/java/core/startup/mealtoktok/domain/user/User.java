package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class User {
    private Long userId;
    private UserInfo userInfo;
    private OAuthInfo oAuthInfo;
    private UserRole userRole;
    private Set<String> deviceTokens;
    private UserDateInfo userDateInfo;
}
