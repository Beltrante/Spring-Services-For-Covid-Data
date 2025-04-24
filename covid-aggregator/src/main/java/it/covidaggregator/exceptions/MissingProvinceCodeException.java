package it.covidaggregator.exceptions;

public class MissingProvinceCodeException extends RuntimeException {
    public MissingProvinceCodeException(String message) {
        super(message);
    }
}
