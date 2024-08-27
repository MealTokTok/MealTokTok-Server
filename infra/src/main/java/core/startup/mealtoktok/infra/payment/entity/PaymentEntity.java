package core.startup.mealtoktok.infra.payment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.payment.Payment;
import core.startup.mealtoktok.domain.payment.PaymentMethod;
import core.startup.mealtoktok.domain.payment.PaymentProvider;
import core.startup.mealtoktok.domain.payment.PaymentState;
import core.startup.mealtoktok.infra.order.entity.MoneyConverter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String paymentKey;

    private String orderId;

    @Convert(converter = MoneyConverter.class)
    private Money payAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentProvider paymentProvider;

    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;

    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    public static PaymentEntity from(Payment payment) {
        return PaymentEntity.builder()
                .paymentId(payment.getPaymentId())
                .paymentKey(payment.getPaymentKey())
                .orderId(payment.getOrderId().toString())
                .payAmount(payment.getPayAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentProvider(payment.getPaymentProvider())
                .paymentState(payment.getPaymentState())
                .requestedAt(payment.getRequestedAt())
                .approvedAt(payment.getApprovedAt())
                .build();
    }

    public Payment toDomain() {
        return Payment.builder()
                .paymentId(paymentId)
                .paymentKey(paymentKey)
                .orderId(OrderId.from(orderId))
                .payAmount(payAmount)
                .paymentMethod(paymentMethod)
                .paymentProvider(paymentProvider)
                .paymentState(paymentState)
                .requestedAt(requestedAt)
                .approvedAt(approvedAt)
                .build();
    }
}
