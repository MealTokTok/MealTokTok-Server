package core.startup.mealtoktok.domain.global.exception;

import core.startup.mealtoktok.common.exception.ApiException;
import core.startup.mealtoktok.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class DomainException extends ApiException {

    public DomainException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public DomainException(ErrorCode errorCode) {
        super(errorCode, null);
    }
}
