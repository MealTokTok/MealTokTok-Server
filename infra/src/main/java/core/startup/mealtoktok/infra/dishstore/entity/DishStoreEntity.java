package core.startup.mealtoktok.infra.dishstore.entity;

import static core.startup.mealtoktok.infra.jpa.util.CoordinateConvertor.convertToPoint;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.locationtech.jts.geom.Point;

import lombok.*;

import core.startup.mealtoktok.domain.dishstore.DishStore;
import core.startup.mealtoktok.domain.dishstore.DishStoreInfo;
import core.startup.mealtoktok.domain.dishstore.OperatingHour;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;
import core.startup.mealtoktok.domain.user.Coordinate;
import core.startup.mealtoktok.infra.jpa.entity.AddressVO;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "dish_store")
public class DishStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long storeId;

    private String storeName;

    private String phoneNumber;

    @Column(columnDefinition = "POINT SRID 4326")
    private Point coordinate;

    @Embedded private AddressVO address;

    private LocalTime openTime;
    private LocalTime closeTime;

    public DishStore toDomain() {
        return DishStore.builder()
                .storeId(storeId)
                .dishStoreInfo(
                        DishStoreInfo.of(
                                storeName,
                                phoneNumber,
                                AddressWithCoordinate.of(
                                        address.toDomain(),
                                        Coordinate.of(coordinate.getX(), coordinate.getY())),
                                OperatingHour.of(openTime, closeTime)))
                .build();
    }

    public static DishStoreEntity from(DishStoreInfo dishStoreInfo) {
        return DishStoreEntity.builder()
                .storeName(dishStoreInfo.storeName())
                .phoneNumber(dishStoreInfo.phoneNumber())
                .coordinate(convertToPoint(dishStoreInfo.addressWithCoordinate().coordinate()))
                .address(AddressVO.from(dishStoreInfo.addressWithCoordinate().address()))
                .openTime(dishStoreInfo.operatingHour().openTime())
                .closeTime(dishStoreInfo.operatingHour().closeTime())
                .build();
    }

    public void update(DishStoreInfo dishStoreInfo) {
        this.storeName = dishStoreInfo.storeName();
        this.phoneNumber = dishStoreInfo.phoneNumber();
        this.coordinate = convertToPoint(dishStoreInfo.addressWithCoordinate().coordinate());
        this.address = AddressVO.from(dishStoreInfo.addressWithCoordinate().address());
        this.openTime = dishStoreInfo.operatingHour().openTime();
        this.closeTime = dishStoreInfo.operatingHour().closeTime();
    }
}
