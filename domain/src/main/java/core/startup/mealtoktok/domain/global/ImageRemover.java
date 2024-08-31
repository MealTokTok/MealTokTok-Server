package core.startup.mealtoktok.domain.global;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;

@Component
@RequiredArgsConstructor
public class ImageRemover {

    private final ImageRepository imageRepository;

    public void remove(Image image) {
        imageRepository.deleteById(image);
    }
}
