package core.startup.mealtoktok.domain.mealdelivery;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import core.startup.mealtoktok.domain.mealdelivery.exception.MealDeliveryDomainException;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MealDelivery {

    private Long mealDeliveryId;
    private String orderId;
    private OrderTypeForDelivery orderType;
    private OrderedMeal orderedMeal;
    private DeliveryState deliveryState;
    private DeliveryDateTime deliveryDateTime;

    public void deliveryRequest() {
        this.deliveryDateTime = DeliveryDateTime.deliveryRequest(deliveryDateTime);
        this.deliveryState = DeliveryState.DELIVERY_REQUESTED;
    }

    public void startDelivery() {
        if (deliveryState != DeliveryState.DELIVERY_REQUESTED) {
            throw new MealDeliveryDomainException("배송예약없이 배송을 시작할 수 없습니다.");
        }

        this.deliveryDateTime = DeliveryDateTime.start(deliveryDateTime);
        this.deliveryState = DeliveryState.DELIVERING;
    }

    public void completeDelivery() {
        if (deliveryState != DeliveryState.DELIVERING) {
            throw new MealDeliveryDomainException("배송중이 아닌 주문은 배송완료처리할 수 없습니다.");
        }

        this.deliveryDateTime = DeliveryDateTime.complete(deliveryDateTime);
        this.deliveryState = DeliveryState.DELIVERED;
    }

    public static MealDelivery create(
            String orderId, OrderTypeForDelivery orderTypeForDelivery, OrderedMeal orderedMeal) {
        return new MealDelivery(
                null,
                orderId,
                orderTypeForDelivery,
                orderedMeal,
                DeliveryState.PENDING,
                DeliveryDateTime.init());
    }

    public boolean hasFullDiningOption() {
        return orderedMeal.hasFullDiningOption();
    }
}
