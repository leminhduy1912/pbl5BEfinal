package com.pbl5.utils.exceptions.dbException;


import com.pbl5.utils.enums.ErrorCode;
import com.pbl5.utils.enums.ErrorStatusCode;
import com.pbl5.utils.exceptions.Exception;

public class DuplicateEntryException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DuplicateEntryException(String message) {
        this.message = message;
        this.errorCode = ErrorCode.DuplicateEntryException.getValue();
        this.statusCode = ErrorStatusCode.DuplicateEntryException.getValue();
    }
}
