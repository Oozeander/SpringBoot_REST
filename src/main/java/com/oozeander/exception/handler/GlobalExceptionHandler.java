package com.oozeander.exception.handler;

import com.oozeander.exception.StudentAlreadyExistsException;
import com.oozeander.exception.StudentNotFoundException;
import com.oozeander.model.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleStudentAlreadyExists(StudentAlreadyExistsException exception,
                                                                        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ExceptionResponse(LocalDateTime.now(), HttpStatus.FOUND.value(),
                        HttpStatus.FOUND.getReasonPhrase(), List.of(exception.getLocalizedMessage()),
                        request.getRequestURI()));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleStudentNotFound(StudentNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(), List.of(ex.getLocalizedMessage()), request.getRequestURI())
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getFieldErrors().stream()
                .map(error -> error.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
                        errors, getUri(request.getDescription(false))));
    }

    private String getUri(String uri) {
        return uri.replace("uri=", "");
    }
}