package core.startup.mealtoktok.api.global.util;

import java.util.Collections;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import core.startup.mealtoktok.common.dto.File;

public class FileMapper {

    public static List<File> toFiles(List<MultipartFile> files) {
        if (files == null) {
            return Collections.emptyList();
        }

        return files.stream().map(File::from).toList();
    }
}
