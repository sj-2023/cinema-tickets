package uk.gov.dwp.uc.pairtest.exception;

public class InvalidPurchaseException extends RuntimeException {

    //Constructor that accepts a message
    public InvalidPurchaseException(String message){
        super(message);
    }

}
