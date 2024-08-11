package core.startup.mealtoktok.domain.dishstore;

import java.time.LocalTime;

import core.startup.mealtoktok.domain.user.Address;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;

public record DishStoreInfo(
        String storeName,
        String phoneNumber,
        AddressWithCoordinate addressWithCoordinate,
        OperatingHour operatingHour) {
    public static DishStoreInfo of(
            String storeName,
            String phoneNumber,
            Address address,
            Double latitude,
            Double longitude,
            LocalTime openTime,
            LocalTime closeTime) {
        return new DishStoreInfo(
                storeName,
                phoneNumber,
                AddressWithCoordinate.of(address, latitude, longitude),
                OperatingHour.of(openTime, closeTime));
    }

    public static DishStoreInfo of(
            String storeName,
            String phoneNumber,
            AddressWithCoordinate addressWithCoordinate,
            OperatingHour operatingHour) {
        return new DishStoreInfo(storeName, phoneNumber, addressWithCoordinate, operatingHour);
    }
}
