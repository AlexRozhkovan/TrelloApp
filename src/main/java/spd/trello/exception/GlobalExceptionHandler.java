package spd.trello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import spd.trello.domain.parent_classes.Domain;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler<T extends Domain> {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handle(RuntimeException ex, WebRequest request) {
        String responseBody = ex.getMessage();
        return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(RuntimeException ex, WebRequest request) {
        String responseBody = ex.getMessage();
        return new ResponseEntity(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        T target = (T) ex.getTarget();
        String name = target.getClass().getSimpleName();

        List details = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse(name + " not valid", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    protected ResponseEntity<Object> onConstraintValidationException(
            ConstraintViolationException e) {
        List<String> details = e.getConstraintViolations().stream().map(er -> er.getMessage()).collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse("Validation error", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}