package core.startup.mealtoktok.infra.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.Orderer;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class OrdererVO {

    @Column(name = "orderer_id")
    private Long userId;

    public static OrdererVO from(Orderer orderer) {
        return new OrdererVO(orderer.userId());
    }

    public Orderer toDomain() {
        return new Orderer(userId);
    }
}
