package core.startup.mealtoktok.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ApiException extends RuntimeException {

    protected final ErrorCode errorCode;
    protected final String message;

    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("%s. %s", errorCode.getMessage(), message);
        }
    }

}
