package core.startup.mealtoktok.domain.auth.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class AlreadyRegisteredUserException extends DomainException {

    public static final AlreadyRegisteredUserException EXCEPTION =
            new AlreadyRegisteredUserException();

    private AlreadyRegisteredUserException() {
        super(AuthErrorCode.ALREADY_REGISTERED);
    }
}
