package dmitreev.testwork.javalightapp.error.handler;

import dmitreev.testwork.javalightapp.error.exception.AccessException;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.error.exception.NotFoundException;
import dmitreev.testwork.javalightapp.error.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBadRequestException(MethodArgumentNotValidException e) {
        log.error("Bad request exception.");
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,
                "The conditions for the requested operation are not met.", e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(final ConflictException e) {
        log.error("Conflict exception.");
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT,
                "The request contradicts the established restrictions.",
                e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessException.class)
    public ResponseEntity<Object> handleAccessException(final AccessException e) {
        log.error("Access exception.");
        return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN,
                "The request contradicts the established restrictions, there is no access.",
                e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(final NotFoundException e) {
        log.error("Not found exception.");
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, "The required object was not found.",
                e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(Throwable e) {
        log.error("Internal server error.");
        return buildResponseEntity(new ApiError("Invalid request.",
                e.getMessage(), LocalDateTime.now()));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}