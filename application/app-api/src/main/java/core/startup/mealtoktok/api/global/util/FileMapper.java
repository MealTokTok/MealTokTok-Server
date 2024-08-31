package core.startup.mealtoktok.api.global.util;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import core.startup.mealtoktok.domain.global.File;

public class FileMapper {

    public static List<File> toFiles(List<MultipartFile> files) {
        if (files == null) {
            return Collections.emptyList();
        }

        return files.stream()
                .filter(file -> file != null && !file.isEmpty())
                .map(
                        file -> {
                            try {
                                return File.of(file.getContentType(), file.getInputStream());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                .toList();
    }

    public static File toFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            return File.of(file.getContentType(), file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
