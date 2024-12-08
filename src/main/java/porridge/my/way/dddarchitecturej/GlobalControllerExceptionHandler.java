package porridge.my.way.dddarchitecturej;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private <T extends Exception> void throwWhenThereIsAnAnnotation(T e) throws T {
        // If the exception is annotated with @ResponseStatus rethrow it and let the framework handle it.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        throwWhenThereIsAnAnnotation(e);
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IllegalArgumentDomainException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentHandler(HttpServletRequest req, IllegalArgumentDomainException e) throws IllegalArgumentDomainException {
        throwWhenThereIsAnAnnotation(e);
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String message = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";"));
        return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.BAD_REQUEST);
    }
}
