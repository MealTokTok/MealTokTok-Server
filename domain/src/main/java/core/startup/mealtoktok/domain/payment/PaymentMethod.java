package core.startup.mealtoktok.domain.payment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {
    EASY_PAY("간편결제"),
    CARD("카드");

    private final String method;

    public static PaymentMethod from(String method) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.method.equals(method)) {
                return paymentMethod;
            }
        }
        throw new IllegalArgumentException("No matching PaymentMethod for: " + method);
    }
}
