package core.startup.mealtoktok.infra.mealdelivery.entity;

import java.time.LocalDate;
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

import core.startup.mealtoktok.domain.mealdelivery.DeliveryDateTime;
import core.startup.mealtoktok.domain.mealdelivery.DeliveryState;
import core.startup.mealtoktok.domain.mealdelivery.MealDelivery;
import core.startup.mealtoktok.domain.mealdelivery.OrderedMeal;
import core.startup.mealtoktok.domain.order.ReservedTime;

@Entity
@Table(name = "meal_delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MealDeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealDeliveryId;

    private Long orderId;

    private Long mealId;
    private boolean includeRice;

    private boolean hasFullDiningOption;
    private LocalDate reservedDate;

    @Enumerated(EnumType.STRING)
    private ReservedTime reservedTime;

    @Enumerated(EnumType.STRING)
    private DeliveryState deliveryState;

    private LocalDateTime deliveryStartTime;
    private LocalDateTime deliveryCompleteTime;

    public static MealDeliveryEntity from(MealDelivery mealDelivery) {
        return MealDeliveryEntity.builder()
                .orderId(mealDelivery.getOrderId())
                .mealId(mealDelivery.getOrderedMeal().mealId())
                .includeRice(mealDelivery.getOrderedMeal().includeRice())
                .reservedDate(mealDelivery.getOrderedMeal().reservedSchedule().reservedDate())
                .reservedTime(mealDelivery.getOrderedMeal().reservedSchedule().reservedTime())
                .hasFullDiningOption(mealDelivery.getOrderedMeal().hasFullDiningOption())
                .deliveryState(mealDelivery.getDeliveryState())
                .deliveryStartTime(mealDelivery.getDeliveryDateTime().deliveryStartTime())
                .deliveryCompleteTime(mealDelivery.getDeliveryDateTime().deliveryCompleteTime())
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
                .deliveryDateTime(DeliveryDateTime.of(deliveryStartTime, deliveryCompleteTime))
                .build();
    }

    public void update(MealDelivery mealDelivery) {
        this.deliveryState = mealDelivery.getDeliveryState();
        this.deliveryStartTime = mealDelivery.getDeliveryDateTime().deliveryStartTime();
        this.deliveryCompleteTime = mealDelivery.getDeliveryDateTime().deliveryCompleteTime();
    }
}
