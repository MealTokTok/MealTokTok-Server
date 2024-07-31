package core.startup.mealtoktok.domain.user;

public record DeliveryAddress(
    AddressWithCoordinate addressWithCoordinate,
    AddressStatus status
) {

  public static DeliveryAddress configure(AddressWithCoordinate addressWithCoordinate) {
    return new DeliveryAddress(addressWithCoordinate,
        AddressStatus.CONFIGURED
    );
  }

  public static DeliveryAddress notConfigure(AddressWithCoordinate addressWithCoordinate) {
    return new DeliveryAddress(addressWithCoordinate,
        AddressStatus.NOT_CONFIGURED
    );
  }

  public static DeliveryAddress configure(Double latitude,
      Double longitude,
      AddressStatus status,
      String city,
      String street,
      String detail) {
    return new DeliveryAddress(
        AddressWithCoordinate.of(latitude, longitude, city, street, detail),
        status
    );
  }
}
