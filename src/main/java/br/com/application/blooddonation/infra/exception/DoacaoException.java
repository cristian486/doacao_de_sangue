package br.com.application.blooddonation.infra.exception;

public class DoacaoException extends RuntimeException {

    public DoacaoException() {
    }

    public DoacaoException(String message) {
        super(message);
    }
}
