package com.restservice.example.restServices.exceptions;

import com.restservice.example.restServices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//Default spring error handler if not good for you then you can customize the errors like this and get a similiar response
// through out your organization or application of microservices as you can have dotnet or next js framework running parrallalely in the same app


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                MethodArgumentNotValidException ex,
                HttpHeaders headers,
                HttpStatus status,
                WebRequest request) {

            // Your custom logic to format and return the error response
            // (e.g., include the list of validation errors with field names and messages)
            ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());

            return new ResponseEntity(exceptionresponse, HttpStatus.BAD_REQUEST);
        }



    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)  {
        ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionresponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request)  {
        ExceptionResponse exceptionresponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionresponse, HttpStatus.NOT_FOUND);
    }

}
