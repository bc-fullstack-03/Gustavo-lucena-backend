package br.com.bootcam.sysmap.api.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{

    public ResourceNotFoundExceptions(String msg){
        super(msg);
    }
}