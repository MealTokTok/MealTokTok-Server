package core.startup.mealtoktok.domain.user;

import core.startup.mealtoktok.domain.auth.OAuthInfo;
import core.startup.mealtoktok.domain.auth.OAuthProfile;
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
    AddressWithCoordinate addressWithCoordinate;
    private Set<String> deviceTokens;
    private UserDateInfo userDateInfo;

    public void update(OAuthProfile oAuthProfile, String deviceToken) {
        this.userInfo = userInfo.updateBy(oAuthProfile);
        this.deviceTokens.add(deviceToken);
    }
}
