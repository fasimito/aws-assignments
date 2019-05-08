package com.aws.utils;

public class RequestLimitException extends Exception{
    private static final long serialVersionUID = 1399825358754654702L;
    public RequestLimitException(){
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message){
        super(message);
    }
}
