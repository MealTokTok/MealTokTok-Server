package core.startup.mealtoktok.api.auth.dto;

import core.startup.mealtoktok.domain.auth.OAuthTokens;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;

public record SignupRequest(
        OAuthTokens oAuthTokens,
        AddressInfo addressInfo
) {

    public record AddressInfo(
            String address,
            Double latitude,
            Double longitude
    ) { }

    public AddressWithCoordinate toInfo() {
        return AddressWithCoordinate.of(addressInfo.address(), addressInfo.latitude(),  addressInfo.longitude());
    }
}
