package core.startup.mealtoktok.domain.mealdelivery;

import java.time.LocalDateTime;

public record DeliveryDateTime(
        LocalDateTime deliveryStartTime, LocalDateTime deliveryCompleteTime) {

    public static DeliveryDateTime of(
            LocalDateTime deliveryStartTime, LocalDateTime deliveryCompleteTime) {
        return new DeliveryDateTime(deliveryStartTime, deliveryCompleteTime);
    }

    public static DeliveryDateTime start(LocalDateTime deliveryStartTime) {
        return new DeliveryDateTime(deliveryStartTime, null);
    }

    public static DeliveryDateTime complete(
            LocalDateTime deliveryStartTime, LocalDateTime deliveryCompleteTime) {
        return new DeliveryDateTime(deliveryStartTime, deliveryCompleteTime);
    }

    public static DeliveryDateTime init() {
        return new DeliveryDateTime(null, null);
    }
}
