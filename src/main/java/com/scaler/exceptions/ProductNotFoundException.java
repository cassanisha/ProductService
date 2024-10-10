package com.scaler.exceptions;

import lombok.Getter;
import lombok.Setter;

//checked exception as it extends Exception
@Getter
@Setter
public class ProductNotFoundException extends Exception {
    public Long id;
    public String message;
    public ProductNotFoundException(Long id, String message){
        super(message);
        this.id=id;
        this.message=message;
    }
}
