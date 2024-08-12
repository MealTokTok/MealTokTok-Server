package core.startup.mealtoktok.infra.mealdelivery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.mealdelivery.CollectingState;
import core.startup.mealtoktok.domain.mealdelivery.FullDining;

@Entity
@Table(name = "full_dining")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FullDiningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fullDiningId;

    private Long mealDeliveryId;

    @Enumerated(EnumType.STRING)
    private CollectingState collectingState;

    private LocalDateTime collectedDateTime;

    public static FullDiningEntity from(FullDining fullDining) {
        return FullDiningEntity.builder()
                .mealDeliveryId(fullDining.getMealDeliveryId())
                .collectingState(fullDining.getCollectState())
                .collectedDateTime(fullDining.getCollectedDateTime())
                .build();
    }

    public FullDining toDomain() {
        return FullDining.builder()
                .fullDiningId(fullDiningId)
                .mealDeliveryId(mealDeliveryId)
                .collectState(collectingState)
                .collectedDateTime(collectedDateTime)
                .build();
    }

    public void update(FullDining fullDining) {
        this.collectingState = fullDining.getCollectState();
        this.collectedDateTime = fullDining.getCollectedDateTime();
    }
}
