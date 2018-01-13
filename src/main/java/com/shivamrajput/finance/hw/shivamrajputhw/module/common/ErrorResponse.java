package com.shivamrajput.finance.hw.shivamrajputhw.module.common;



/**
 *
 */
public class ErrorResponse extends GenericResponse{

    public ErrorResponse(String type, String message) {
        super(type,message);
    }
    public ErrorResponse(String message) {
        super(GenericResponse.ERROR,message);
    }


}
