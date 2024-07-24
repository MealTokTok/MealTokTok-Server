package core.startup.mealtoktok.api.auth.exception;

import core.startup.mealtoktok.common.exception.WebException;
import core.startup.mealtoktok.domain.auth.exception.AuthErrorCode;

public class UnAuthorizedUserException extends WebException {

    public static final UnAuthorizedUserException EXCEPTION = new UnAuthorizedUserException();

    private UnAuthorizedUserException() {
        super(AuthErrorCode.UNAUTHORIZED_USER);
    }

}
