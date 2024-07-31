package core.startup.mealtoktok.api.global.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import core.startup.mealtoktok.common.dto.Response;
import core.startup.mealtoktok.common.exception.CustomException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> customError(CustomException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus())
                .body(
                        Response.error(
                                e.getErrorCode().getErrorReason(),
                                request.getRequestURI(),
                                e.getMessage()));
    }
}
