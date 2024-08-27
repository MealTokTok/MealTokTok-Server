package core.startup.mealtoktok.domain.payment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentProvider {
    TOSS_PAY("토스페이");

    private final String provider;

    public static PaymentProvider from(String provider) {
        for (PaymentProvider paymentProvider : PaymentProvider.values()) {
            if (paymentProvider.provider.equals(provider)) {
                return paymentProvider;
            }
        }
        throw new IllegalArgumentException("No matching PaymentProvider for: " + provider);
    }
}
