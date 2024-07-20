package core.startup.mealtoktok.api.user.request;

import core.startup.mealtoktok.domain.user.AddressInfo;

public record UpdateAddressRequest(
        String address,
        Double latitude,
        Double longitude
) {
    public AddressInfo toInfo() {
        return AddressInfo.of(latitude, longitude, address);
    }
}
