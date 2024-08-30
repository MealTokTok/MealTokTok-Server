package core.startup.mealtoktok.domain.global;

import core.startup.mealtoktok.common.dto.Image;

public interface ImageRepository {

    Image findById(TargetImage targetImage);
}
