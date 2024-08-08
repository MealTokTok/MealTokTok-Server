package core.startup.mealtoktok.api.dishstore.request;

import java.time.LocalTime;

import core.startup.mealtoktok.domain.dishstore.DishStoreInfo;
import core.startup.mealtoktok.domain.user.Address;

public record DishStoreRequest(
        String storeName,
        String phoneNumber,
        Address address,
        Double latitude,
        Double longitude,
        LocalTime openTime,
        LocalTime closeTime) {

    public DishStoreInfo toDishStoreInfo() {
        return DishStoreInfo.of(
                storeName, phoneNumber, address, latitude, longitude, openTime, closeTime);
    }
}
