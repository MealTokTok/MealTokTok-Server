package core.startup.mealtoktok.api.auth.dto;

import core.startup.mealtoktok.domain.auth.OAuthTokens;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;

public record SignupRequest(
        OAuthTokens oAuthTokens, String deviceToken, AddressInfoRequest addressInfoRequest) {

    public record AddressInfoRequest(String address, Double latitude, Double longitude) {

        public AddressWithCoordinate toAddressWithCoordinate() {
            return AddressWithCoordinate.of(address, latitude, longitude);
        }
    }

    public AddressWithCoordinate toAddressWithCoordinate() {
        return addressInfoRequest.toAddressWithCoordinate();
    }
}
