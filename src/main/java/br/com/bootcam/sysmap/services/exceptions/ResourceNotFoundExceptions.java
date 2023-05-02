package br.com.bootcam.sysmap.services.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{

    public ResourceNotFoundExceptions(String msg){
        super(msg);
    }
}