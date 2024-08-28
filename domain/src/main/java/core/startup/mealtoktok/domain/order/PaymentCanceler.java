package core.startup.mealtoktok.domain.order;

public interface PaymentCanceler {

    void cancel(Order order, String cancelReason);
}
