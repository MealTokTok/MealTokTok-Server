package core.startup.mealtoktok.domain.global;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;

@Component
@RequiredArgsConstructor
public class ImageUpdater {

    private final ImageAppender imageAppender;
    private final ImageRemover imageRemover;

    public Image update(Image existingImage, Image image) {
        imageRemover.remove(existingImage);
        return imageAppender.append(image);
    }
}
