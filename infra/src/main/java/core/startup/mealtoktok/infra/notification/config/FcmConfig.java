package core.startup.mealtoktok.infra.notification.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FcmConfig {

    @Value("${fcm.secret-key}")
    private String fcmSecretKey;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FirebaseOptions options =
                FirebaseOptions.builder()
                        .setCredentials(getDecodedCredentials(fcmSecretKey))
                        .build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    private GoogleCredentials getDecodedCredentials(String encodedKey) throws IOException {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return GoogleCredentials.fromStream(new ByteArrayInputStream(decodedKey));
    }
}
