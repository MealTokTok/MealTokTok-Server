package core.startup.mealtoktok.api.user.request;

import core.startup.mealtoktok.domain.auth.OAuthToken;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;

public record SignupRequest(
        String idToken,
        String accessToken,
        AddressInfo addressInfo
) {

    public OAuthToken toOAuthToken() {
        return OAuthToken.of(accessToken, idToken);
    }

    public AddressWithCoordinate toInfo() {
        return AddressWithCoordinate.of(addressInfo.address(), addressInfo.latitude(),  addressInfo.longitude());
    }
}
