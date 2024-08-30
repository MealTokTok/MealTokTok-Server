package core.startup.mealtoktok.infra.global.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.global.exception.ImageErrorCode;

public class ImageNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new ImageNotFoundException();

    private ImageNotFoundException() {
        super(ImageErrorCode.IMAGE_NOT_FOUND);
    }
}
