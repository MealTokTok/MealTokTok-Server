package core.startup.mealtoktok.infra.auth.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.auth.exception.AuthErrorCode;

public class OtherServerUnauthorizedException extends InfraException {

    public static final OtherServerUnauthorizedException EXCEPTION = new OtherServerUnauthorizedException();

    private OtherServerUnauthorizedException() {
        super(AuthErrorCode.OTHER_SERVER_UNAUTHORIZED);
    }
}
