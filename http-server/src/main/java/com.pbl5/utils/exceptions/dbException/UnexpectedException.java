package com.pbl5.utils.exceptions.dbException;
import com.pbl5.utils.enums.ErrorCode;
import com.pbl5.utils.enums.ErrorStatusCode;
import com.pbl5.utils.exceptions.Exception;

public class UnexpectedException extends Exception {
    public UnexpectedException() {
        this.message = "Somethings Went Wrongs";
        this.statusCode = ErrorStatusCode.UnexpectedException.getValue();
        this.errorCode = ErrorCode.UnexpectedException.getValue();
    }
}
