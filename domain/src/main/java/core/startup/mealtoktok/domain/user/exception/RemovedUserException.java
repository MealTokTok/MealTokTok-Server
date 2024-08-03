package core.startup.mealtoktok.domain.user.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class RemovedUserException extends DomainException {

    public static final RemovedUserException EXCEPTION = new RemovedUserException();

    private RemovedUserException() {
        super(UserErrorCode.REMOVED_USER);
    }
}
