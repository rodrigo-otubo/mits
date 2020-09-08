package com.mitsburguer.app.error;

import com.mitsburguer.app.error.exception.ClientExistsException;
import com.mitsburguer.app.error.exception.ClientNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ClientExistsException.class)
    public ResponseEntity<ApiError> handleClientExistsException(ClientExistsException exception, Locale locale){
        return new ResponseEntity(buildApiError(exception.getErrorCode(), locale), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ApiError> handleClientNotFoundException(ClientNotFoundException exception, Locale locale){
        return new ResponseEntity(buildApiError(exception.getErrorCode(), locale), HttpStatus.NOT_FOUND);
    }

    private ApiError buildApiError(String errorCode, Locale locale, String... args){
        String errorMessage = "";
        try {
            errorMessage = messageSource.getMessage(errorCode, args, locale);

        }catch (NoSuchMessageException ex){
            errorMessage = "No error message available";
        }

        return new ApiError(errorCode, errorMessage);
    }

}
