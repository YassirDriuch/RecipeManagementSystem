package recipes.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomExceptionMessage> handleNotFound(NotFoundException e, WebRequest request) {
        CustomExceptionMessage body = new CustomExceptionMessage(
                LocalTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                e.getMessage(),
                request.getDescription(false)
        );
        log.info("[Exception | handleNotFound] Recipe not found. method thrown");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomExceptionMessage> handleBadRequest(BadRequestException e, WebRequest request) {
        CustomExceptionMessage body = new CustomExceptionMessage(
                LocalTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                e.getMessage(),
                request.getDescription(false)
        );
        log.info("[Exception | handleBadRequest] Bad Request. method thrown");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<CustomExceptionMessage> handleForbidden(ForbiddenException e, WebRequest request) {
        CustomExceptionMessage body = new CustomExceptionMessage(
                LocalTime.now(),
                HttpStatus.FORBIDDEN.value(),
                "Forbidden",
                e.getMessage(),
                request.getDescription(false)
        );
        log.info("[Exception | handleForbidden] Illegal modification. method thrown");
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

}
