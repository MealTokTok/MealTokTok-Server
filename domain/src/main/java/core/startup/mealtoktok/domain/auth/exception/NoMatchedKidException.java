package core.startup.mealtoktok.domain.auth.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class NoMatchedKidException extends DomainException {

    public static final DomainException EXCEPTION = new NoMatchedKidException();

    private NoMatchedKidException() {
        super(AuthErrorCode.NO_MATCHED_KID);
    }
}
