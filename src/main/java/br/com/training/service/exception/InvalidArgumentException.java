package br.com.training.service.exception;

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String msg){
        super(msg);
    }

    public InvalidArgumentException(String msg, Throwable cause){
        super(msg, cause);
    }
}
