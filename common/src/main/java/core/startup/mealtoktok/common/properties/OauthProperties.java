package core.startup.mealtoktok.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OauthProperties {

    public static String BASE_URL;
    public static String CLIENT_ID;
    public static String REDIRECT_URL;
    public static String APP_ID;
    public static String ADMIN_KEY;
    public static String SERVICE_KEY;

    public static final String KAKAO_OAUTH_QUERY_STRING = "/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code&scope=openid";

    @Value("${oauth.kakao.service-key}")
    public void setServiceKey(String serviceKey) {
        OauthProperties.SERVICE_KEY = serviceKey;
    }

    @Value("${oauth.kakao.base-url}")
    public void setBaseUrl(String baseUrl) {
        OauthProperties.BASE_URL = baseUrl;
    }

    @Value("${oauth.kakao.client-id}")
    public void setClientId(String clientId) {
        OauthProperties.CLIENT_ID = clientId;
    }

    @Value("${oauth.kakao.redirect-url}")
    public void setRedirectUrl(String redirectUrl) {
        OauthProperties.REDIRECT_URL = redirectUrl;
    }

    @Value("${oauth.kakao.app-id}")
    public void setAppId(String appId) {
        OauthProperties.APP_ID = appId;
    }

    @Value("${oauth.kakao.admin-key}")
    public void setAdminKey(String adminKey) {
        OauthProperties.ADMIN_KEY = adminKey;
    }
}
