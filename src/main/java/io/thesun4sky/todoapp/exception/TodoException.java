package io.thesun4sky.todoapp.exception;

public class TodoException extends IllegalArgumentException{
    public TodoException(String message) {
        super(message);
    }
}
