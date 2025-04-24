package it.covidextractor.exceptions.types;

public class CovidDataSourceException extends RuntimeException {
    public CovidDataSourceException(String message) {
        super(message);
    }
}
