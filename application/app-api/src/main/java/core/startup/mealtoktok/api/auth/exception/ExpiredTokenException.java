package core.startup.mealtoktok.api.auth.exception;

import core.startup.mealtoktok.common.exception.WebException;
import core.startup.mealtoktok.domain.auth.exception.AuthErrorCode;

public class ExpiredTokenException extends WebException {
    public static final ExpiredTokenException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(AuthErrorCode.EXPIRED_TOKEN);
    }
}
