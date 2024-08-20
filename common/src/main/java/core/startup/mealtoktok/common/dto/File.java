package core.startup.mealtoktok.common.dto;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public record File(String contentType, InputStream inputStream) {

    public static File from(MultipartFile multipartFile) {
        try {

            if (multipartFile == null) {
                return null;
            }

            return new File(multipartFile.getContentType(), multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
