package core.startup.mealtoktok.infra.toss.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@RequiredArgsConstructor
public class TossAuthRequestInterceptor implements RequestInterceptor {

    @Value("${toss-payment.secret-key}")
    private String secretKey;

    @Override
    public void apply(RequestTemplate template) {
        template.header(
                "Authorization",
                "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()));
    }
}
