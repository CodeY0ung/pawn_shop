package solo.pawnshop.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import solo.pawnshop.operator.exception.DuplicatedEmailException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<Void> handleDuplicatedEmail(
            DuplicatedEmailException e
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .build();
    }
}
