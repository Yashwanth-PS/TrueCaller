package com.TrueCaller.exception;

public class ContactAlreadySpammedException extends RuntimeException{
    public ContactAlreadySpammedException() {
    }

    public ContactAlreadySpammedException(String message) {
        super(message);
    }

    public ContactAlreadySpammedException(String message, Throwable cause) {
        super(message, cause);
    }
}
