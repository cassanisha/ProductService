package com.scaler.exceptionhandler;

import com.scaler.dtos.ExceptionDto;
import com.scaler.dtos.ProductNotFoundDTo;
import com.scaler.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//To handle exceptions globally. centralized exception handling. controller's output passed via this class and sends its ouTput to client.
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
//        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        return responseEntity;
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Arithmetic exception");
        exceptionDto.setResolution("Nothing can be done");
        //HTTP status can be seen by inspecting.
        ResponseEntity<ExceptionDto> responseEntity=new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundDTo> handleProductNotFoundException(ProductNotFoundException e ){
            ProductNotFoundDTo DTO = new ProductNotFoundDTo();
            DTO.setMessage("Product with id: "+ e.getId()+ " not found");
            ResponseEntity<ProductNotFoundDTo> responseEntity=new ResponseEntity<>(DTO, HttpStatus.NOT_FOUND);
            return responseEntity;
    }


}
