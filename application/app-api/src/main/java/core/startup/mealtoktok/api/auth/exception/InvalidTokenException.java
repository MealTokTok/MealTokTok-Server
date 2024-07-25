package core.startup.mealtoktok.api.auth.exception;

import core.startup.mealtoktok.common.exception.DomainException;
import core.startup.mealtoktok.domain.auth.exception.AuthErrorCode;

public class InvalidTokenException extends DomainException {

    public static final DomainException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(AuthErrorCode.INVALID_TOKEN);
    }
}
