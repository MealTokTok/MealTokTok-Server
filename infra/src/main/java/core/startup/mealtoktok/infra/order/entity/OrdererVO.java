package core.startup.mealtoktok.infra.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.Orderer;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrdererVO {

    @Column(name = "orderer_id")
    private Long userId;

    private Long deliveryAddressId;

    public OrdererVO(Long userId, Long deliveryAddressId) {
        this.userId = userId;
        this.deliveryAddressId = deliveryAddressId;
    }

    public static OrdererVO from(Orderer orderer) {
        return new OrdererVO(orderer.userId(), orderer.deliveryAddressId());
    }

    public Orderer toDomain() {
        return new Orderer(userId, deliveryAddressId);
    }

    // Getters and other necessary methods
}
