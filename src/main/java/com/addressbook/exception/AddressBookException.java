package com.addressbook.exception;

public class AddressBookException extends RuntimeException {
    public AddressBookException(String message) {
        super(message);
    }

    public AddressBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
