package com.pbl5.utils.exceptions.dbException;

import com.pbl5.utils.enums.ErrorCode;
import com.pbl5.utils.enums.ErrorStatusCode;
import com.pbl5.utils.exceptions.Exception;

public class ForeignKeyViolationException extends Exception {
    public ForeignKeyViolationException(String message) {
        this.message = message;
        this.statusCode = ErrorStatusCode.ForeignKeyViolationException.getValue();
        this.errorCode = ErrorCode.ForeignKeyViolationException.getValue();
    }
}
