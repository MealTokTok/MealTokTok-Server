package core.startup.mealtoktok.domain.global;

import java.io.InputStream;

public record File(String contentType, InputStream inputStream) {

    public static File of(String contentType, InputStream inputStream) {
        return new File(contentType, inputStream);
    }
}
