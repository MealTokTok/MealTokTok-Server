package core.startup.mealtoktok.domain.order;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private Long orderId;
    private OrderType orderType;
    private OrderState orderState;
    private String specialInstruction;
    private Orderer orderer;
    private OrderPrice orderPrice;
    private LocalDateTime orderTime;

    public static Order create(
            Orderer orderer,
            OrderType orderType,
            String specialInstruction,
            OrderPrice orderPrice) {
        return Order.builder()
                .orderType(orderType)
                .orderState(OrderState.PENDING)
                .specialInstruction(specialInstruction)
                .orderer(orderer)
                .orderPrice(orderPrice)
                .build();
    }

    public boolean isOrderBy(Orderer orderer) {
        return this.orderer.isSameUser(orderer);
    }
}
