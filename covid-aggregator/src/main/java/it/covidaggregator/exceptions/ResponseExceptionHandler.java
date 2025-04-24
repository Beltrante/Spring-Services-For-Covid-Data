package it.covidaggregator.exceptions;

import it.covidaggregator.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MissingProvinceCodeException.class})
    public ResponseEntity<Response<String>> handleNotFoundException(MissingProvinceCodeException ex) {
        return getResponseEntity(
                ex.getMessage(),
                ex.getMessage(),
                404
        );
    }

    private ResponseEntity<Response<String>> getResponseEntity(String causeException, String message, int status) {
        log.error(causeException);
        Response<String> response = Response.<String>builder()
                .type(Response.Type.ERROR)
                .description(message)
                .status(status).build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(status));
    }
}