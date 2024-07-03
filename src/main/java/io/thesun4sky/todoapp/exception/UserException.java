package io.thesun4sky.todoapp.exception;

public class UserException extends IllegalArgumentException {
    public UserException(String message) {
        super(message);
    }
}
