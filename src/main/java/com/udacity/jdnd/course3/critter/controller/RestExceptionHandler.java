package com.udacity.jdnd.course3.critter.controller;
import com.udacity.jdnd.course3.critter.dto.ErrorResponseDTO;
import com.udacity.jdnd.course3.critter.exception.*;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    protected ResponseEntity<Object> handleCustomerNotFound( CustomerNotFoundException e) {
        return buildResponseEntity(getNotFoundResponse(e));
    }

    @ExceptionHandler(PetNotFoundException.class)
    protected ResponseEntity<Object> handlePetNotFound( PetNotFoundException e) {
        return buildResponseEntity(getNotFoundResponse(e));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    protected ResponseEntity<Object> handleEmployeeNotFound( EmployeeNotFoundException e) {
        return buildResponseEntity(getNotFoundResponse(e));
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    protected ResponseEntity<Object> handleScheduleNotFound( ScheduleNotFoundException e) {
        return buildResponseEntity(getNotFoundResponse(e));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleCustomerNotFound( RuntimeException e) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Resource Not Available");
        return buildResponseEntity(errorResponseDTO);
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleCustomerNotFound( DataAccessException e) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.CONFLICT, "DB Server Not Available");
        return buildResponseEntity(errorResponseDTO);
    }

    public ErrorResponseDTO getNotFoundResponse(RuntimeException e){
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND, e.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponseDTO errorResponseDTO) {
        return new ResponseEntity<>(errorResponseDTO, errorResponseDTO.getStatus());
    }



}
