package core.startup.mealtoktok.domain.payment;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.order.OrderId;

public interface PaymentGateway {

    Payment confirm(String paymentKey, OrderId orderId, Money amount);

    void cancel(String paymentKey, String cancelReason);
}
