package core.startup.mealtoktok.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class CustomException extends RuntimeException {

    protected final BaseErrorCode errorCode;
    protected final String message;

    public CustomException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    public String getMessage() {
        if (message == null) {
            return errorCode.getErrorReason().message();
        } else {
            return String.format("%s. %s", errorCode.getErrorReason().message(), message);
        }
    }

}
