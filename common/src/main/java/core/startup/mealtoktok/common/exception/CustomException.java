package core.startup.mealtoktok.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class CustomException extends RuntimeException {

    protected final BaseErrorCode errorCode;
    protected final String sourceLayer;

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(errorCode.getErrorReason().status());
    }

    public String getMessage() {
        if (sourceLayer == null) {
            return errorCode.getErrorReason().message();
        } else {
            return String.format("%s - %s", sourceLayer, errorCode.getErrorReason().message());
        }
    }
}
