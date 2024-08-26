package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryDateTime {

    private final LocalDateTime deliveryRequestTime;
    private final LocalDateTime deliveryStartTime;
    private final LocalDateTime deliveryCompleteTime;

    public LocalDateTime getDeliveryRequestTime() {
        return deliveryRequestTime;
    }

    public LocalDateTime getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public LocalDateTime getDeliveryCompleteTime() {
        return deliveryCompleteTime;
    }

    public static DeliveryDateTime of(
            LocalDateTime deliveryReservedTime,
            LocalDateTime deliveryStartTime,
            LocalDateTime deliveryCompleteTime) {
        return new DeliveryDateTime(deliveryReservedTime, deliveryStartTime, deliveryCompleteTime);
    }

    public static DeliveryDateTime deliveryRequest(DeliveryDateTime deliveryDateTime) {
        return new DeliveryDateTime(LocalDateTime.now(), null, null);
    }

    public static DeliveryDateTime start(DeliveryDateTime deliveryDateTime) {
        return new DeliveryDateTime(
                deliveryDateTime.getDeliveryRequestTime(), LocalDateTime.now(), null);
    }

    public static DeliveryDateTime complete(DeliveryDateTime deliveryDateTime) {
        return new DeliveryDateTime(
                deliveryDateTime.getDeliveryRequestTime(),
                deliveryDateTime.getDeliveryStartTime(),
                LocalDateTime.now());
    }

    public static DeliveryDateTime init() {
        return new DeliveryDateTime(null, null, null);
    }
}
