package core.startup.mealtoktok.api.dishstore.response;

import java.time.LocalTime;

import core.startup.mealtoktok.domain.dishstore.DishStore;
import core.startup.mealtoktok.domain.user.Address;

public record DishStoreResponse(
        String storeName,
        String phoneNumber,
        Address address,
        Double latitude,
        Double longitude,
        LocalTime openTime,
        LocalTime closeTime) {
    public static DishStoreResponse from(DishStore dishStore) {
        return new DishStoreResponse(
                dishStore.getStoreName(),
                dishStore.getPhoneNumber(),
                dishStore.getAddressWithCoordinate().address(),
                dishStore.getAddressWithCoordinate().coordinate().longitude(),
                dishStore.getAddressWithCoordinate().coordinate().longitude(),
                dishStore.getOperatingHour().openTime(),
                dishStore.getOperatingHour().closeTime());
    }
}
