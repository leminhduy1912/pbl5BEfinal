package com.pbl5.utils.exceptions.dbException;

import com.pbl5.utils.enums.ErrorCode;
import com.pbl5.utils.enums.ErrorStatusCode;
import com.pbl5.utils.exceptions.Exception;


public class NotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        this.message = message;
        this.errorCode = ErrorCode.NotFoundException.getValue();
        this.statusCode = ErrorStatusCode.NotFoundException.getValue();
    }
}
