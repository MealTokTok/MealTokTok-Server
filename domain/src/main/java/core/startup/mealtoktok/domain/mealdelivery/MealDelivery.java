package core.startup.mealtoktok.domain.mealdelivery;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import core.startup.mealtoktok.domain.mealdelivery.exception.MealDeliveryDomainException;
import core.startup.mealtoktok.domain.order.MealDeliveryReservationInfo;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.order.OrderType;
import core.startup.mealtoktok.domain.order.OrderedMeal;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MealDelivery {

    private MealDeliveryId mealDeliveryId;
    private OrderId orderId;
    private OrderType orderType;
    private OrderedMeal orderedMeal;
    private DeliveryState deliveryState;
    private DeliveryDateTime deliveryDateTime;

    public static MealDelivery create(MealDeliveryReservationInfo info) {
        return new MealDelivery(
                null,
                info.orderId(),
                info.orderType(),
                info.orderedMeal(),
                DeliveryState.PENDING,
                DeliveryDateTime.init());
    }

    public void requestDelivery() {
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

    public boolean hasFullDiningOption() {
        return orderedMeal.hasFullDiningOption();
    }
}
