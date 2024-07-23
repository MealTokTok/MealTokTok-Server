package core.startup.mealtoktok.infra.auth.exception;

import core.startup.mealtoktok.common.exception.InfraException;

public class OtherServerForbiddenException extends InfraException {

    public static final InfraException EXCEPTION = new OtherServerForbiddenException();

    private OtherServerForbiddenException() {
        super(AuthErrorCode.OTHER_SERVER_FORBIDDEN);
    }
}
