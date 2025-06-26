package com.logic.springjwt.ExceptionHandler;

import com.logic.springjwt.response.MessageResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateQueryException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ExceptionLoginBlocked.class)
    public ResponseEntity<?> handleLoginBlocked(ExceptionLoginBlocked ex) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Maximum login attempts reached. Login blocked for 10 minutes. Please try again later."));
    }

    @ExceptionHandler(ExceptionDataNotSaved.class)
    public ResponseEntity<?> handleDataNotSaved(ExceptionDataNotSaved ex) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Data not saved due to invalid data binding or internal server error."));
    }

    @ExceptionHandler(ExceptionInvalidSystemAccess.class)
    public ResponseEntity<?> handleInvalidSystemAccess(ExceptionInvalidSystemAccess ex) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Login blocked due to invalid system access or missing role."));
    }

    @ExceptionHandler(ExceptionInvalidFileXY.class)
    public ResponseEntity<?> handleInvalidFile(ExceptionInvalidFileXY ex) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Invalid file attached. Please check the file size and dimensions."));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleFileSizeExceeded(MaxUploadSizeExceededException ex) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("File size exceeds limit. Please re-upload with compression."));
    }

    @ExceptionHandler(ExceptionDataAlreadyExists.class)
    public ResponseEntity<?> handleDataAlreadyExists(ExceptionDataAlreadyExists ex) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Data already exists. Please update."));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {
        List<String> errorList = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            Object rejectedValue = ((FieldError) error).getRejectedValue();
            errorList.add("Value rejected: '" + rejectedValue + "'.");
        }
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Invalid characters exist. Please check your inputs.<br/><br/>" + errorList));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponse("Validation failed: " + errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errorList = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorList.add("Field '" + violation.getPropertyPath() + "' - " + violation.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponse("Data Validation Error: " + errorList));
    }

    @ExceptionHandler(HibernateError.class)
    public ResponseEntity<?> handleHibernateError(HibernateError ex) {
        return buildGenericErrorResponse(ex.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<?> handleHibernateException(HibernateException ex) {
        return buildGenericErrorResponse(ex.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HibernateQueryException.class)
    public ResponseEntity<?> handleHibernateQueryException(HibernateQueryException ex) {
        return buildGenericErrorResponse(ex.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> handleBadRequest(BadRequest ex) {
        return buildGenericErrorResponse(ex.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<?> handleInternalServerError(InternalServerError ex) {
        return buildGenericErrorResponse(ex.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDatabaseError(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("message", "Internal server error: " + ex.getMessage()));
    }

    private ResponseEntity<?> buildGenericErrorResponse(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }


}
