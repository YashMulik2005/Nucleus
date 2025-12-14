package com.example.Nucleus.exception;

import com.example.Nucleus.dto.ErrorResponseHandler;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?>HandleNotFoundException(NotFoundException ex){
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.NOT_FOUND, false, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?>HandleUserNotFoundException(UserNotFoundException ex){
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.NOT_FOUND, false, ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<?>HandleUserAlreadyExistException(UserAlreadyExist ex){
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.CONFLICT, false, ex.getMessage());
    }

    @ExceptionHandler(ParameterMissingException.class)
    public ResponseEntity<?>HandleParameterMissingException(ParameterMissingException ex){
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.BAD_REQUEST,false,ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.NOT_FOUND,false,ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.UNAUTHORIZED,false,ex.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException ex) {
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.UNAUTHORIZED,false,ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.FORBIDDEN,false,ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR,false,ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(Exception ex){
        return ErrorResponseHandler.ErrorResponseBuilder(HttpStatus.BAD_REQUEST,false, ex.getMessage());
    }
}
