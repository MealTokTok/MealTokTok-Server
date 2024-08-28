package core.startup.mealtoktok.infra.feign.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.auth.exception.AuthErrorCode;

public class OtherServerExpiredTokenException extends InfraException {

    public static final InfraException EXCEPTION = new OtherServerExpiredTokenException();

    private OtherServerExpiredTokenException() {
        super(AuthErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
    }
}
