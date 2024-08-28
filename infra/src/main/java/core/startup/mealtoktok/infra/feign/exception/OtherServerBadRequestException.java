package core.startup.mealtoktok.infra.feign.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.auth.exception.AuthErrorCode;

public class OtherServerBadRequestException extends InfraException {

    public static final InfraException EXCEPTION = new OtherServerBadRequestException();

    private OtherServerBadRequestException() {
        super(AuthErrorCode.OTHER_SERVER_BAD_REQUEST);
    }
}
