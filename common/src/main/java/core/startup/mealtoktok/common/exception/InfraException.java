package core.startup.mealtoktok.common.exception;

import lombok.Getter;

@Getter
public class InfraException extends CustomException {

    public InfraException(BaseErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public InfraException(BaseErrorCode errorCode) {
        super(errorCode, null);
    }
}
