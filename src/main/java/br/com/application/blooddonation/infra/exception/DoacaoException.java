package br.com.application.blooddonation.infra.exception;

public class DoacaoException extends RuntimeException {

    public DoacaoException() {
    }

    public DoacaoException(Throwable cause) {
        super(cause);
    }

    public DoacaoException(String message) {
        super(message);
    }
}
