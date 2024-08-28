package core.startup.mealtoktok.api.payment;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.api.payment.dto.PaymentCancelRequest;
import core.startup.mealtoktok.api.payment.dto.PaymentFailReason;
import core.startup.mealtoktok.api.payment.dto.PaymentRequest;
import core.startup.mealtoktok.api.payment.dto.PaymentResponse;
import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.domain.payment.PaymentId;
import core.startup.mealtoktok.domain.payment.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentApi {

    private final PaymentService paymentService;

    @PostMapping("/success")
    public Response<PaymentResponse> pay(PaymentRequest request) {
        return Response.success(
                PaymentResponse.from(
                        paymentService.pay(
                                request.paymentKey(), request.toOrderId(), request.toAmount())));
    }

    @PostMapping("/fail")
    public Response<Void> fail(PaymentFailReason paymentFailReason) {
        paymentService.fail(paymentFailReason.message(), paymentFailReason.toOrderId());
        return Response.error(paymentFailReason.code(), paymentFailReason.message());
    }

    @PostMapping("/{paymentId}/cancel")
    public Response<Void> cancel(
            @PathVariable Long paymentId, @RequestBody PaymentCancelRequest request) {
        paymentService.cancel(PaymentId.from(paymentId), request.cancelReason());
        return Response.success();
    }
}
