package core.startup.mealtoktok.infra.global.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.ImageRepository;
import core.startup.mealtoktok.domain.global.TargetImage;
import core.startup.mealtoktok.infra.global.exception.ImageNotFoundException;
import core.startup.mealtoktok.infra.jpa.entity.ImageEntity;

@Repository
@Transactional
@RequiredArgsConstructor
public class CoreImageRepository implements ImageRepository {

    private final JpaImageRepository jpaImageRepository;

    @Override
    public Image findById(TargetImage targetImage) {
        return jpaImageRepository
                .findById(targetImage.imageId())
                .map(ImageEntity::toDomain)
                .orElseThrow(() -> ImageNotFoundException.EXCEPTION);
    }
}
