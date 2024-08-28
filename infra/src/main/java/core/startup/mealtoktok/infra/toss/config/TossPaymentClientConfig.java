package core.startup.mealtoktok.infra.toss.config;

import org.springframework.context.annotation.Bean;

public class TossPaymentClientConfig {

    @Bean
    public TossAuthRequestInterceptor feignInterceptor() {
        return new TossAuthRequestInterceptor();
    }
}
