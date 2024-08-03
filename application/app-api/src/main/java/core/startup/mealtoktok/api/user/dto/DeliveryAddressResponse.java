package core.startup.mealtoktok.api.user.dto;

import core.startup.mealtoktok.domain.user.Address;
import core.startup.mealtoktok.domain.user.AddressStatus;
import core.startup.mealtoktok.domain.user.DeliveryAddress;

public record DeliveryAddressResponse(Address address, AddressStatus addressStatus) {

    public static DeliveryAddressResponse from(DeliveryAddress deliveryAddress) {
        return new DeliveryAddressResponse(
                deliveryAddress.addressWithCoordinate().address(), deliveryAddress.status());
    }
}
