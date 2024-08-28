package core.startup.mealtoktok.infra.payment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import core.startup.mealtoktok.domain.order.Money;
import core.startup.mealtoktok.domain.order.OrderId;
import core.startup.mealtoktok.domain.payment.Payment;
import core.startup.mealtoktok.domain.payment.PaymentId;
import core.startup.mealtoktok.domain.payment.PaymentMethod;
import core.startup.mealtoktok.domain.payment.PaymentProvider;
import core.startup.mealtoktok.domain.payment.PaymentState;
import core.startup.mealtoktok.infra.order.entity.MoneyConverter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "payment")
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

    private String failedReason;

    private String canceledReason;

    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    public static PaymentEntity from(Payment payment) {
        return PaymentEntity.builder()
                .paymentKey(payment.getPaymentKey())
                .orderId(payment.getOrderId().toString())
                .payAmount(payment.getPayAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentProvider(payment.getPaymentProvider())
                .paymentState(payment.getPaymentState())
                .failedReason(payment.getFailedReason())
                .canceledReason(payment.getCanceledReason())
                .requestedAt(payment.getRequestedAt())
                .approvedAt(payment.getApprovedAt())
                .build();
    }

    public Payment toDomain() {
        return Payment.builder()
                .paymentId(PaymentId.from(paymentId))
                .paymentKey(paymentKey)
                .orderId(OrderId.from(orderId))
                .payAmount(payAmount)
                .paymentMethod(paymentMethod)
                .paymentProvider(paymentProvider)
                .paymentState(paymentState)
                .failedReason(failedReason)
                .canceledReason(canceledReason)
                .requestedAt(requestedAt)
                .approvedAt(approvedAt)
                .build();
    }

    public void update(Payment payment) {
        this.paymentState = payment.getPaymentState();
        this.canceledReason = payment.getCanceledReason();
    }
}
