package core.startup.mealtoktok.domain.global;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;

@Component
@RequiredArgsConstructor
public class ImageReader {

    private final ImageRepository imageRepository;

    public Image read(TargetImage targetImage) {
        return imageRepository.findById(targetImage);
    }
}
