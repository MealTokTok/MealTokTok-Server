package core.startup.mealtoktok.infra.dishstore.entity;

import static core.startup.mealtoktok.infra.global.util.CoordinateConvertor.convertToPoint;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.locationtech.jts.geom.Point;

import lombok.*;

import core.startup.mealtoktok.domain.dishstore.DishStore;
import core.startup.mealtoktok.domain.dishstore.OperatingHour;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;
import core.startup.mealtoktok.domain.user.Coordinate;
import core.startup.mealtoktok.infra.global.vo.Address;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class DishStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long storeId;

    private String storeName;

    private String phoneNumber;

    @Column(columnDefinition = "POINT SRID 4326")
    private Point coordinate;

    @Embedded private Address address;

    private LocalTime openTime;
    private LocalTime closeTime;

    public DishStore toDomain() {
        return DishStore.builder()
                .storeId(storeId)
                .storeName(storeName)
                .phoneNumber(phoneNumber)
                .addressWithCoordinate(
                        AddressWithCoordinate.of(
                                address.toDomain(),
                                Coordinate.of(coordinate.getX(), coordinate.getY())))
                .operatingHour(OperatingHour.of(openTime, closeTime))
                .build();
    }

    public static DishStoreEntity from(DishStore dishStore) {
        return DishStoreEntity.builder()
                .storeId(dishStore.getStoreId())
                .storeName(dishStore.getStoreName())
                .phoneNumber(dishStore.getPhoneNumber())
                .coordinate(convertToPoint(dishStore.getAddressWithCoordinate().coordinate()))
                .address(Address.from(dishStore.getAddressWithCoordinate().address()))
                .openTime(dishStore.getOperatingHour().openTime())
                .closeTime(dishStore.getOperatingHour().closeTime())
                .build();
    }
}
