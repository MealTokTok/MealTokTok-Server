package core.startup.mealtoktok.api.auth.exception;

import core.startup.mealtoktok.common.exception.WebException;
import core.startup.mealtoktok.domain.auth.exception.AuthErrorCode;

public class InvalidTokenException extends WebException {
    public static final InvalidTokenException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(AuthErrorCode.INVALID_TOKEN);
    }
}
