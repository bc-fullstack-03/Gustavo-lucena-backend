package br.com.bootcam.sysmap.api.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
