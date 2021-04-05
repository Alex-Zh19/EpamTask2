package com.epam.alex.task2.exception;

public class PlantException extends Exception {
    public PlantException() {
    }

    public PlantException(String message) {
        super(message);
    }

    public PlantException(Throwable cause) {
        super(cause);
    }

    public PlantException(String message, Throwable cause) {
        super(message, cause);
    }
}
