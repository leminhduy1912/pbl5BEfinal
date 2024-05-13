package com.pbl5.utils.exceptions.dbException;

import com.pbl5.utils.enums.ErrorCode;
import com.pbl5.utils.enums.ErrorStatusCode;
import com.pbl5.utils.exceptions.Exception;

public class UpdateFailedException extends Exception {
    public UpdateFailedException(String message) {
        this.message = message;
        this.errorCode = ErrorCode.UpdateFailedException.getValue();
        this.statusCode = ErrorStatusCode.UpdateFailedException.getValue();
    }
}
