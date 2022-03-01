package sn.ucad.gestionstock.exception;

import lombok.Getter;

import java.util.List;

public class InvalidEntityException extends  RuntimeException{

    @Getter
    private  ErrorCodes errorCodes;

     @Getter
    private List<String> errors;

    public  InvalidEntityException(String message){
        super(message);
    }

    public  InvalidEntityException(String message, Throwable throwable)
    {
        super(message,throwable);
    }

    public  InvalidEntityException(String message, Throwable throwable, ErrorCodes errorCodes)
    {
        super(message,throwable);
        this.errorCodes=errorCodes;
    }

    public  InvalidEntityException(String message, ErrorCodes errorCodes)
    {
        super(message);
        this.errorCodes=errorCodes;
    }

    public  InvalidEntityException(String message, ErrorCodes errorCodes,List<String> errors )
    {
        super(message);
        this.errorCodes=errorCodes;
        this.errors= errors;
    }
}
