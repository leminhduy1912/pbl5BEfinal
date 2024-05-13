package com.pbl5.utils.exceptions.dbException;

import com.pbl5.utils.enums.ErrorCode;
import com.pbl5.utils.enums.ErrorStatusCode;
import com.pbl5.utils.exceptions.Exception;

public class InvalidPropertiesException extends Exception {
    public InvalidPropertiesException(String message) {
        this.message = message;
        this.errorCode = ErrorCode.InvalidPropertiesException.getValue();
        this.statusCode = ErrorStatusCode.InvalidPropertiesException.getValue();
    }
}
