package core.startup.mealtoktok.infra.gcs.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class GcsConfig {

    @Value("${spring.cloud.gcp.storage.credentials.encoded-key}")
    private String gcpKey;

    @Value("${spring.cloud.gcp.storage.project-id}")
    private String projectId;

    @Bean
    public Storage storage() throws IOException {
        GoogleCredentials credentials = getDecodedCredentials(gcpKey);
        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(credentials)
                .build()
                .getService();
    }

    private GoogleCredentials getDecodedCredentials(String encodedKey) throws IOException {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return GoogleCredentials.fromStream(new ByteArrayInputStream(decodedKey));
    }
}
