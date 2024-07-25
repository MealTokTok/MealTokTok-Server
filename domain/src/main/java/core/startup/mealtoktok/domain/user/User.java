package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private Long userId;
    private UserInfo userInfo;
    private OAuthInfo oAuthInfo;
    private UserRole userRole;
    private Set<String> deviceTokens;
    private UserDateInfo userDateInfo;
}
