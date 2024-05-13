package com.pbl5.utils.exceptions.dbException;


import com.pbl5.utils.enums.ErrorCode;
import com.pbl5.utils.enums.ErrorStatusCode;
import com.pbl5.utils.exceptions.Exception;

public class CreateFailedException extends Exception {
    public CreateFailedException(String message) {
        this.message = message;
        this.errorCode = ErrorCode.CreateFailedException.getValue();
        this.statusCode = ErrorStatusCode.CreateFailedException.getValue();

    }
}
