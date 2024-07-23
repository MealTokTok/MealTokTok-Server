package core.startup.mealtoktok.infra.auth.exception;

import core.startup.mealtoktok.common.exception.InfraException;

public class OtherServerBadRequestException extends InfraException {

    public static final InfraException EXCEPTION = new OtherServerBadRequestException();

    public OtherServerBadRequestException() {
        super(AuthErrorCode.OTHER_SERVER_BAD_REQUEST);
    }
}
