package core.startup.mealtoktok.infra.user.entity;

import static core.startup.mealtoktok.infra.jpa.util.CoordinateConvertor.convertToPoint;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.locationtech.jts.geom.Point;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import core.startup.mealtoktok.domain.user.AddressStatus;
import core.startup.mealtoktok.domain.user.AddressWithCoordinate;
import core.startup.mealtoktok.domain.user.Coordinate;
import core.startup.mealtoktok.domain.user.DeliveryAddress;
import core.startup.mealtoktok.domain.user.TargetUser;
import core.startup.mealtoktok.infra.jpa.entity.AddressVO;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "delivery_address")
public class DeliveryAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryAddressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter
    private UserEntity user;

    @Column(columnDefinition = "POINT SRID 4326")
    private Point coordinate;

    @Enumerated(EnumType.STRING)
    private AddressStatus status;

    @Embedded private AddressVO address;

    public static DeliveryAddressEntity from(UserEntity user, DeliveryAddress deliveryAddress) {
        return DeliveryAddressEntity.builder()
                .deliveryAddressId(deliveryAddress.getDeliveryAddressId())
                .user(user)
                .coordinate(convertToPoint(deliveryAddress.getAddressWithCoordinate().coordinate()))
                .status(deliveryAddress.getStatus())
                .address(AddressVO.from(deliveryAddress.getAddressWithCoordinate().address()))
                .build();
    }

    public static DeliveryAddressEntity from(
            TargetUser targetUser, DeliveryAddress deliveryAddress) {
        return DeliveryAddressEntity.builder()
                .user(UserEntity.from(targetUser))
                .coordinate(convertToPoint(deliveryAddress.getAddressWithCoordinate().coordinate()))
                .status(deliveryAddress.getStatus())
                .address(AddressVO.from(deliveryAddress.getAddressWithCoordinate().address()))
                .build();
    }

    public DeliveryAddress toDomain() {
        return DeliveryAddress.of(
                deliveryAddressId,
                AddressWithCoordinate.of(
                        address.toDomain(), Coordinate.of(coordinate.getX(), coordinate.getY())),
                status);
    }

    public void update(DeliveryAddress deliveryAddress) {
        this.coordinate = convertToPoint(deliveryAddress.getAddressWithCoordinate().coordinate());
        this.status = deliveryAddress.getStatus();
        this.address = AddressVO.from(deliveryAddress.getAddressWithCoordinate().address());
    }
}
