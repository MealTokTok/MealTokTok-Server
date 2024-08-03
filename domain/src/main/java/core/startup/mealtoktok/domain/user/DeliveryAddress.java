package core.startup.mealtoktok.domain.user;

public record DeliveryAddress(
        Long deliveryAddressId, AddressWithCoordinate addressWithCoordinate, AddressStatus status) {

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
}
