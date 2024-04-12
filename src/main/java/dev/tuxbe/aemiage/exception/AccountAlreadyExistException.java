package dev.tuxbe.aemiage.exception;

public class AccountAlreadyExistException extends RuntimeException{
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
