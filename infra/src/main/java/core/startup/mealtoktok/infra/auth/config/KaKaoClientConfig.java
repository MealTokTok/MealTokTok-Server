package core.startup.mealtoktok.infra.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

@Import(KakaoErrorDecoder.class)
public class KaKaoClientConfig {

    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public KakaoErrorDecoder commonFeignErrorDecoder() {
        return new KakaoErrorDecoder();
    }

    @Bean
    Encoder formEncoder() {
        return new feign.form.FormEncoder();
    }
}
