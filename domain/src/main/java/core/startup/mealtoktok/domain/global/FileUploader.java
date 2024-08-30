package core.startup.mealtoktok.domain.global;

import java.util.List;

import core.startup.mealtoktok.common.dto.Image;

public interface FileUploader {

    List<Image> upload(List<File> files);

    Image upload(File file);
}
