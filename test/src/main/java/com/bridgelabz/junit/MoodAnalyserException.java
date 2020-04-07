package com.bridgelabz.junit;

public class MoodAnalyserException extends Exception{
    public enum ExceptionType{
        ENTERED_NULL, NO_SUCH_METHOD, METHOD_INVOCATION_ISSUE, NO_SUCH_FIELD, NO_SUCH_OBJECT, ENTERED_EMPTY, ILLEGAL_EXCEPTION
    }
    public ExceptionType type;

    public MoodAnalyserException( ExceptionType type,String message) {
        super( message );
        this.type = type;
    }


}
