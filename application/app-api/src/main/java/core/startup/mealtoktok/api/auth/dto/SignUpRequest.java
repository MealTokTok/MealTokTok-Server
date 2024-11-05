package core.startup.mealtoktok.api.auth.dto;

import core.startup.mealtoktok.domain.auth.OAuthTokens;
import core.startup.mealtoktok.domain.user.Address;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;

public record SignUpRequest(OAuthTokens oAuthTokens, String deviceToken) {

    public record AddressInfoRequest(Address address, Double latitude, Double longitude) {

        public AddressWithCoordinate toAddressWithCoordinate() {
            return AddressWithCoordinate.of(address, latitude, longitude);
        }
    }
}
