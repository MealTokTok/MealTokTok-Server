package core.startup.mealtoktok.infra.fulldining.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.fulldining.exception.FullDiningErrorCode;

public class FullDiningNotFoundException extends InfraException {

    public static final FullDiningNotFoundException EXCEPTION = new FullDiningNotFoundException();

    private FullDiningNotFoundException() {
        super(FullDiningErrorCode.FULL_DINING_NOT_FOUND);
    }
}
