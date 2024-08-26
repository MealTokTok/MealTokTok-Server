package core.startup.mealtoktok.common.dto;

import java.util.List;

public interface FileUploader {

    List<Image> upload(List<File> files);

    Image upload(File file);
}
