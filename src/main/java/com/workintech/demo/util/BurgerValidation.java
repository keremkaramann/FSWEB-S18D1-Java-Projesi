package com.workintech.demo.util;

import com.workintech.demo.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {
    public static void checkName(String name){
        if(name==null || name.isEmpty()){
            throw new BurgerException("Name is null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkId(Long id){
        if(id==null || id<0){
            throw new BurgerException("This Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }
}
