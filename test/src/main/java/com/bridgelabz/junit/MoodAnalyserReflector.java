package com.bridgelabz.junit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyserReflector {

    public static Constructor<?> getConstructor(Class<?> ... param) throws MoodAnalyserException {
        Class<?> moodAnalyserClass = null;
        try {
            moodAnalyserClass = Class.forName( "com.bridgelabz.junit.MoodAnalyser" );
            Constructor<?> constructor=moodAnalyserClass.getConstructor( param );
            return constructor;
        } catch (ClassNotFoundException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.NO_SUCH_FIELD, "ENTER PROPER class NAME" );

        } catch (NoSuchMethodException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.NO_SUCH_METHOD, "enter proper method name" );

        }

    }
    public static Object createMoodAnalyser(Constructor<?>constructor,Object ... message) throws MoodAnalyserException{
        try{
            Object moodAnalyserObject=constructor.newInstance( message );
            return moodAnalyserObject;
        } catch (IllegalAccessException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.ILLEGAL_EXCEPTION,"issue with illegal data enter" );
        } catch (InstantiationException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.NO_SUCH_OBJECT,"issue with object creation" );
        } catch (InvocationTargetException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.METHOD_INVOCATION_ISSUE,"issue with method invocation" );
        }
    }


    public static Object invokeMethod(MoodAnalyser moodObject, String methodName) throws MoodAnalyserException {
        try {
            return moodObject.getClass().getMethod( methodName ).invoke( moodObject );
        } catch (InvocationTargetException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.METHOD_INVOCATION_ISSUE, "issue with date entered" );
        } catch (NoSuchMethodException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.NO_SUCH_METHOD, "enter proper method name" );
        } catch (IllegalAccessException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.ILLEGAL_EXCEPTION, "entered illegal name" );
        }
    }

    public static void setFieldValue(MoodAnalyser moodObject, String fieldName, String fieldValue) throws MoodAnalyserException {
        try {
            Field field = moodObject.getClass().getDeclaredField( fieldName );
            field.setAccessible( true );
            field.set( moodObject, fieldValue );
        } catch (NoSuchFieldException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.NO_SUCH_FIELD, "ENTER PROPER FIELD NAME" );
        } catch (IllegalAccessException e) {
            throw new MoodAnalyserException( MoodAnalyserException.ExceptionType.METHOD_INVOCATION_ISSUE, "ISSUE WITH DATA ENTERED" );
        }
    }

}
