package com.shivamrajput.finance.hw.shivamrajputhw.module.common;

/**
 * Created by codebeast
 */
public class SuccessResponse extends GenericResponse {

    public SuccessResponse(String type, String extra) {
        super(type,extra);

    }
    public SuccessResponse(String extra) {
        super(GenericResponse.SUCCESS,extra);

    }


}
