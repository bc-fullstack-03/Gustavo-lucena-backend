package br.com.bootcam.sysmap.api.exceptions;

public class MethodNotAllowedException extends RuntimeException{

    public MethodNotAllowedException(String message) {
        super(message);
    }
}
