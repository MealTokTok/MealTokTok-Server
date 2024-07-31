package core.startup.mealtoktok.infra.user.entity;

import core.startup.mealtoktok.domain.user.AddressStatus;
import core.startup.mealtoktok.domain.user.DeliveryAddress;
import core.startup.mealtoktok.domain.user.Coordinate;
import jakarta.persistence.Column;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "address_with_coordinate")
public class DeliveryAddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long addressWithCoordinateId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @Column(columnDefinition = "POINT SRID 4326")
  private Point coordinate;

  @Enumerated(EnumType.STRING)
  private AddressStatus status;

  private String city;
  private String street;
  private String detail;

  public static DeliveryAddressEntity from(DeliveryAddress deliveryAddress) {
    return DeliveryAddressEntity.builder()
        .coordinate(convertToPoint(deliveryAddress.addressWithCoordinate().coordinate()))
        .status(deliveryAddress.status())
        .city(deliveryAddress.addressWithCoordinate().address().city())
        .street(deliveryAddress.addressWithCoordinate().address().street())
        .detail(deliveryAddress.addressWithCoordinate().address().detail())
        .build();
  }

  public DeliveryAddress toDomain() {
    return DeliveryAddress.configure(
        coordinate.getX(),
        coordinate.getY(),
        status,
        city,
        street,
        detail
    );
  }

  private static Point convertToPoint(Coordinate coordinate) {
    GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    return geometryFactory.createPoint(
        new org.locationtech.jts.geom.Coordinate(coordinate.longitude(), coordinate.latitude()));
  }
}
