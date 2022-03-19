package sn.ucad.gestionstock.exception;

import lombok.Getter;

import java.util.List;

public class InvalidOperationException extends RuntimeException {


    @Getter
    private  ErrorCodes errorCodes;

    public  InvalidOperationException(String message){
        super(message);
    }

    public  InvalidOperationException(String message, Throwable throwable)
    {
        super(message,throwable);
    }

    public  InvalidOperationException(String message, Throwable throwable, ErrorCodes errorCodes)
    {
        super(message,throwable);
        this.errorCodes=errorCodes;
    }

    public  InvalidOperationException(String message, ErrorCodes errorCodes)
    {
        super(message);
        this.errorCodes=errorCodes;
    }
}
