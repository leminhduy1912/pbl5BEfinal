package com.pbl5.utils.exceptions.apiException;

import com.pbl5.utils.enums.ErrorCode;
import com.pbl5.utils.enums.ErrorStatusCode;
import com.pbl5.utils.exceptions.Exception;

public class InvalidEndpointException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidEndpointException() {
        this.errorCode = ErrorCode.InvalidEndpointException.getValue();
        this.statusCode = ErrorStatusCode.InvalidEndpointException.getValue();
        this.message = "URL Not Found";
}
}
