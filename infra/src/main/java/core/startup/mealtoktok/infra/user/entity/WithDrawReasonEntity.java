package core.startup.mealtoktok.infra.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.user.WithDrawReason;

@Entity
@Table(name = "withdraw_reason")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WithDrawReasonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long withDrawReasonId;

    Long userId;

    String reason;

    public WithDrawReasonEntity(Long userId, String reason) {
        this.userId = userId;
        this.reason = reason;
    }

    public static WithDrawReasonEntity from(WithDrawReason withDrawReason) {
        return new WithDrawReasonEntity(withDrawReason.userId(), withDrawReason.reason());
    }
}
