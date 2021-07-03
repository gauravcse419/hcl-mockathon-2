package com.hcl.hackathon.exception;

public class OrderManagementException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int errorCode;
    private final  String errorMessage;


    public int getErrorCode() {
        return errorCode;
    }


    public String getErrorMessage() {
        return errorMessage;
    }


    public OrderManagementException(int errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
