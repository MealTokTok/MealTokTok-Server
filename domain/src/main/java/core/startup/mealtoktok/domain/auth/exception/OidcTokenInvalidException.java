package core.startup.mealtoktok.domain.auth.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class OidcTokenInvalidException extends DomainException {

    public static final DomainException EXCEPTION = new OidcTokenInvalidException();

    private OidcTokenInvalidException() {
        super(AuthErrorCode.OIDC_TOKEN_INVALID);
    }
}
