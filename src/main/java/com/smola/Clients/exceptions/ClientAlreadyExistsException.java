package com.smola.Clients.exceptions;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
