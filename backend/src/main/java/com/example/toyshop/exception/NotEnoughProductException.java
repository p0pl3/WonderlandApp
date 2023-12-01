package com.example.toyshop.exception;

public class NotEnoughProductException extends Exception{
    public NotEnoughProductException() { super(); }
    public NotEnoughProductException(String message) { super(message); }
    public NotEnoughProductException(String message, Throwable cause) { super(message, cause); }
    public NotEnoughProductException(Throwable cause) { super(cause); }
}
