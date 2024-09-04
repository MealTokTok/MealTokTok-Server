package core.startup.mealtoktok.api.global.exception;

import core.startup.mealtoktok.common.exception.WebException;

public class NoPermissionException extends WebException {

    public static final NoPermissionException EXCEPTION = new NoPermissionException();

    private NoPermissionException() {
        super(GlobalErrorCode.PERMISSION_DENIED);
    }
}
