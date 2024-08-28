package core.startup.mealtoktok.infra.toss.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import core.startup.mealtoktok.infra.toss.config.TossPaymentClientConfig;
import core.startup.mealtoktok.infra.toss.dto.TossCancelRequest;
import core.startup.mealtoktok.infra.toss.dto.TossConfirmRequest;
import core.startup.mealtoktok.infra.toss.dto.TossPayment;

@FeignClient(
        name = "toss-client",
        url = "https://api.tosspayments.com/v1/payments",
        configuration = TossPaymentClientConfig.class)
public interface TossPaymentClient {

    @PostMapping("/confirm")
    TossPayment confirm(@RequestBody TossConfirmRequest confirmRequest);

    @PostMapping("/{paymentKey}/cancel")
    void cancel(@PathVariable String paymentKey, @RequestBody TossCancelRequest cancelRequest);
}
