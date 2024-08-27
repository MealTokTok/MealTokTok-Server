package core.startup.mealtoktok.infra.mealdelivery.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
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
import lombok.experimental.SuperBuilder;

import core.startup.mealtoktok.domain.mealdelivery.DeliveryDateTime;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.OrderedMeal;
import core.startup.mealtoktok.domain.order.ReservedTime;
import core.startup.mealtoktok.infra.jpa.config.BaseTimeEntity;

@Entity
@Table(name = "meal_delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class MealDeliveryEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealDeliveryId;

    private String orderId;

    private Long mealId;
    private boolean includeRice;

    private boolean hasFullDiningOption;
    private LocalDate reservedDate;

    @Enumerated(EnumType.STRING)
    private ReservedTime reservedTime;

    @Enumerated(EnumType.STRING)
    private DeliveryState deliveryState;

    @Embedded private DeliveryDateTimeVO deliveryDateTime;

    public static MealDeliveryEntity from(MealDelivery mealDelivery) {
        return MealDeliveryEntity.builder()
                .orderId(mealDelivery.getOrderId())
                .mealId(mealDelivery.getOrderedMeal().mealId())
                .includeRice(mealDelivery.getOrderedMeal().includeRice())
                .reservedDate(mealDelivery.getOrderedMeal().reservedSchedule().reservedDate())
                .reservedTime(mealDelivery.getOrderedMeal().reservedSchedule().reservedTime())
                .hasFullDiningOption(mealDelivery.getOrderedMeal().hasFullDiningOption())
                .deliveryState(mealDelivery.getDeliveryState())
                .deliveryDateTime(DeliveryDateTimeVO.from(mealDelivery.getDeliveryDateTime()))
                .build();
    }

    public MealDelivery toDomain() {
        return MealDelivery.builder()
                .mealDeliveryId(mealDeliveryId)
                .orderId(orderId)
                .orderedMeal(
                        OrderedMeal.create(
                                mealId,
                                reservedDate,
                                reservedTime,
                                includeRice,
                                hasFullDiningOption))
                .deliveryState(deliveryState)
                .deliveryDateTime(
                        (deliveryDateTime == null)
                                ? DeliveryDateTime.init()
                                : deliveryDateTime.toDomain())
                .build();
    }

    public void update(MealDelivery mealDelivery) {
        this.deliveryState = mealDelivery.getDeliveryState();
        this.deliveryDateTime = DeliveryDateTimeVO.from(mealDelivery.getDeliveryDateTime());
    }

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class DeliveryDateTimeVO {

        private LocalDateTime deliveryRequestTime;
        private LocalDateTime deliveryStartTime;
        private LocalDateTime deliveryCompleteTime;

        public static DeliveryDateTimeVO from(DeliveryDateTime deliveryDateTime) {
            return DeliveryDateTimeVO.builder()
                    .deliveryRequestTime(deliveryDateTime.getDeliveryRequestTime())
                    .deliveryStartTime(deliveryDateTime.getDeliveryStartTime())
                    .deliveryCompleteTime(deliveryDateTime.getDeliveryCompleteTime())
                    .build();
        }

        public DeliveryDateTime toDomain() {
            return DeliveryDateTime.of(
                    deliveryRequestTime, deliveryStartTime, deliveryCompleteTime);
        }
    }
}
