package com.bridgelabz.junit;

public class MoodAnalyser {
    private MoodType moodType;
    private String message;
    public enum MoodType{
         happy, sad
    }
    public MoodAnalyser(String mood)
    {
        this.message=mood;
    }

    public MoodAnalyser(String mood,MoodType moodType)
    {
        this.message=mood;
        this.moodType=moodType;
    }
    public MoodAnalyser()
    {

    }
    public String analyseMood(String message) throws MoodAnalyserException
    {
        this.message=message;
        return analyseMood();
    }


    public String analyseMood() throws MoodAnalyserException{
        try {
            if (message.length()==0)
                throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.ENTERED_EMPTY,"enter non empty mood" );
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
