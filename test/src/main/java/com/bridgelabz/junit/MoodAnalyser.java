package com.bridgelabz.junit;

public class MoodAnalyser {
    private String message;
    public MoodAnalyser(String message) {
        this.message = message;
    }

    public String analyseMood(String message) throws MoodAnalyserException
    {
        this.message=message;
        return analyseMood();
    }

    public String analyseMood() throws MoodAnalyserException{
        try {
            if (message.length()==0)
                throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.ENTERED_NULL,"ENTER NON EMPTY MOOD" );
            else if (message.contains( "happy" ))
                return "happy";
            else
                return "sad";
        }
        catch (NullPointerException e)
        {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.ENTERED_NULL,"enter proper mood");
        }
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this.message.equals( ((MoodAnalyser)anotherObject ).message))
            return true;
        return false;

    }


}
