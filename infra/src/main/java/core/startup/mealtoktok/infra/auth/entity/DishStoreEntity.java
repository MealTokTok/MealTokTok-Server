package core.startup.mealtoktok.infra.auth.entity;

import core.startup.mealtoktok.domain.dishstore.DishStore;
import core.startup.mealtoktok.domain.dishstore.OperatingHour;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class DishStoreEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long storeId;

    private String storeName;

    private String phoneNumber;

    private Double latitude;
    private Double longitude;
    private String city;
    private String street;
    private String detail;

    private LocalTime openTime;
    private LocalTime closeTime;

    public DishStore toDomain(){
        return DishStore.builder()
                .storeId(storeId)
                .storeName(storeName)
                .phoneNumber(phoneNumber)
                .addressWithCoordinate(AddressWithCoordinate.of(latitude, longitude, city, street, detail))
                .operatingHour(OperatingHour.of(openTime, closeTime))
                .build();
    }

    public static DishStoreEntity from(DishStore dishStore){
        return DishStoreEntity.builder()
                .storeId(dishStore.getStoreId())
                .storeName(dishStore.getStoreName())
                .phoneNumber(dishStore.getPhoneNumber())
                .latitude(dishStore.getAddressWithCoordinate().coordinate().latitude())
                .longitude(dishStore.getAddressWithCoordinate().coordinate().longitude())
                .city(dishStore.getAddressWithCoordinate().address().city())
                .street(dishStore.getAddressWithCoordinate().address().street())
                .detail(dishStore.getAddressWithCoordinate().address().detail())
                .openTime(dishStore.getOperatingHour().openTime())
                .closeTime(dishStore.getOperatingHour().closeTime())
                .build();
    }
}
