package br.com.bootcam.sysmap.api.exceptions;

public class NoAccessException extends RuntimeException{

    public NoAccessException(String msg) {
        super(msg);
    }
}
