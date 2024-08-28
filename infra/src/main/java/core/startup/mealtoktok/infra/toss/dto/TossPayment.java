package core.startup.mealtoktok.infra.toss.dto;

import java.time.ZonedDateTime;
import java.util.List;

import core.startup.mealtoktok.common.dto.Money;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.payment.Payment;
import core.startup.mealtoktok.domain.payment.PaymentMethod;
import core.startup.mealtoktok.domain.payment.PaymentProvider;
import core.startup.mealtoktok.domain.payment.PaymentState;

public record TossPayment(
        String mId,
        String lastTransactionKey,
        String paymentKey,
        String orderId,
        String orderName,
        long taxExemptionAmount,
        String status,
        ZonedDateTime requestedAt,
        ZonedDateTime approvedAt,
        boolean useEscrow,
        boolean cultureExpense,
        Card card,
        String type,
        EasyPay easyPay,
        long easyPayAmount,
        long easyPayDiscountAmount,
        String country,
        boolean isPartialCancelable,
        Receipt receipt,
        Checkout checkout,
        String currency,
        long totalAmount,
        long balanceAmount,
        long suppliedAmount,
        int vat,
        long taxFreeAmount,
        String method,
        String version,
        List<Cancel> cancels) {

    public Payment toDomain() {
        return Payment.builder()
                .paymentKey(paymentKey)
                .orderId(OrderId.from(orderId))
                .payAmount(Money.from(totalAmount))
                .paymentState(PaymentState.PAYMENT_COMPLETED)
                .paymentMethod(PaymentMethod.from(method))
                .paymentProvider(PaymentProvider.from(easyPay.provider))
                .requestedAt(requestedAt.toLocalDateTime())
                .approvedAt(approvedAt.toLocalDateTime())
                .build();
    }

    public record Card(
            String issuerCode,
            String acquirerCode,
            String number,
            int installmentPlanMonths,
            boolean isInterestFree,
            String approveNo,
            boolean useCardPoint,
            String cardType,
            String ownerType,
            String acquireStatus,
            String receiptUrl,
            int amount) {}

    public record Cancel(
            String transactionKey,
            String cancelReason,
            long taxExemptionAmount,
            ZonedDateTime canceledAt,
            long easyPayDiscountAmount,
            String receiptKey,
            long cancelAmount,
            long taxFreeAmount,
            long refundableAmount,
            String cancelStatus,
            String cancelRequestId) {}

    public record EasyPay(String provider, int amount, int discountAmount) {}

    public record Receipt(String url) {}

    public record Checkout(String url) {}
}
