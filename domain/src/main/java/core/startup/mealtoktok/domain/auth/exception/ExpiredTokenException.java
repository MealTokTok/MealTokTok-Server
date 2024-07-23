package core.startup.mealtoktok.domain.auth.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class ExpiredTokenException extends DomainException {

    public static final ExpiredTokenException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(AuthErrorCode.EXPIRED_TOKEN);
    }
}
