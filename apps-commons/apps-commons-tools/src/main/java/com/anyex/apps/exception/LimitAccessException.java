package com.anyex.apps.exception;

public class LimitAccessException extends RuntimeException
{
    private static final long serialVersionUID = -3608667856397125671L;
    
    public LimitAccessException(String message)
    {
        super(message);
    }
}