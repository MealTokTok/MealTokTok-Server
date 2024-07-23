package core.startup.mealtoktok.domain.auth.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class InvalidTokenException extends DomainException {

    public static final DomainException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(AuthErrorCode.INVALID_TOKEN);
    }
}
