package core.startup.mealtoktok.infra.auth.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.auth.exception.AuthErrorCode;

public class OtherServerForbiddenException extends InfraException {

    public static final InfraException EXCEPTION = new OtherServerForbiddenException();

    private OtherServerForbiddenException() {
        super(AuthErrorCode.OTHER_SERVER_FORBIDDEN);
    }
}
