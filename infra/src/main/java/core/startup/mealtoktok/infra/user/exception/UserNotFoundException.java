package core.startup.mealtoktok.infra.user.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.user.exception.UserErrorCode;

public class UserNotFoundException extends InfraException {

    public static InfraException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
