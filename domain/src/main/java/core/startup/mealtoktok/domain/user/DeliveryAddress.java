package core.startup.mealtoktok.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddress {

    private Long deliveryAddressId;
    private AddressWithCoordinate addressWithCoordinate;
    private AddressStatus status;

    public static DeliveryAddress configure(AddressWithCoordinate addressWithCoordinate) {
        return new DeliveryAddress(null, addressWithCoordinate, AddressStatus.CONFIGURED);
    }

    public static DeliveryAddress notConfigure(AddressWithCoordinate addressWithCoordinate) {
        return new DeliveryAddress(null, addressWithCoordinate, AddressStatus.NOT_CONFIGURED);
    }

    public static DeliveryAddress of(
            Long deliveryAddressId,
            AddressWithCoordinate addressWithCoordinate,
            AddressStatus status) {
        return new DeliveryAddress(deliveryAddressId, addressWithCoordinate, status);
    }

    public void configure() {
        this.status = AddressStatus.CONFIGURED;
    }

    public void unConfigure() {
        this.status = AddressStatus.NOT_CONFIGURED;
    }

    public boolean isConfigured() {
        return status.equals(AddressStatus.CONFIGURED);
    }
}
