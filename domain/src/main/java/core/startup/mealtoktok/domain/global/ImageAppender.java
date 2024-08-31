package core.startup.mealtoktok.domain.global;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;

@Component
@RequiredArgsConstructor
public class ImageAppender {

    private final ImageRepository imageRepository;

    public Image append(Image image) {
        return imageRepository.save(image);
    }
}
