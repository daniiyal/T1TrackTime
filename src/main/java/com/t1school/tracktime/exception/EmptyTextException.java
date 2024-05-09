package com.t1school.tracktime.exception;

import com.t1school.tracktime.annotation.Throw;

@Throw
public class EmptyTextException extends RuntimeException {
    public EmptyTextException() {
        super();
    }

    public EmptyTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyTextException(String message) {
        super(message);
    }

    public EmptyTextException(Throwable cause) {
        super(cause);
    }

}
