package com.bridgelabz.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JunitTest {
    @Test
    public void moodTest() throws MoodAnalyserException {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in sad mood");
        String message = moodAnalyser.analyseMood();
        Assert.assertEquals("sad", message);
    }
    @Test
    public  void happyMoodTest() throws MoodAnalyserException {
        MoodAnalyser moodAnalyser = new MoodAnalyser("I am in happy mood");
        String mood = moodAnalyser.analyseMood();
        Assert.assertEquals("happy", mood);
    }
    @Test
    public void givenNullMood_ShouldReturnHappy() {
        MoodAnalyser moodAnalyser = new MoodAnalyser(null);
        String mood = null;
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect( MoodAnalyserException.class );
            mood = moodAnalyser.analyseMood( );
        } catch (MoodAnalyserException e) {
            Assert.assertEquals( "enter proper mood",e.getMessage() );
        }
    }
    @Test
    public void givenEmptyMood_ShouldReturn_EnterNonEmptyMood() {
        MoodAnalyser moodAnalyser=new MoodAnalyser( "" );
        String mood=null;
        try {
            ExpectedException exceptionRule=ExpectedException.none();
            exceptionRule.expect( MoodAnalyserException.class );
             mood = moodAnalyser.analyseMood();
        } catch (MoodAnalyserException e) {
            Assert.assertEquals( "enter non empty mood",e.getMessage() );
        }
    }
    @Test
    public void givenMoodAnalyserObject_whenProper_ShouldReturnHappy() {
        Constructor<?> constructor = null;
        try {
            try {
                constructor = Class.forName( "com.bridgelabz.junit.MoodAnalyser" ).getConstructor( String.class );
               Object mood=(MoodAnalyser) constructor.newInstance("i am in happy mood");
                MoodAnalyser moodAnalyser = (MoodAnalyser) mood;
                String analyseMood = moodAnalyser.analyseMood();
                Assert.assertEquals( "happy",analyseMood );
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (MoodAnalyserException e) {
                e.printStackTrace();
            }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void givenMoodAnalyserObject_whenProper_ShouldReturnObject() throws MoodAnalyserException {
        Constructor<?> constructor =  MoodAnalyserReflector.getConstructor(String.class);
        MoodAnalyser moodAnalyserObject = (MoodAnalyser) MoodAnalyserReflector.createMoodAnalyser( constructor,"i am in happy mood" );
        Assert.assertEquals( new MoodAnalyser( "i am in happy mood" ),moodAnalyserObject );
    }

    @Test
    public void givenHappyMessage_UsingReflection_ShouldReturnHappy() throws MoodAnalyserException {
        Constructor<?> constructor =  MoodAnalyserReflector.getConstructor(String.class);
        MoodAnalyser moodAnalyserObject = (MoodAnalyser) MoodAnalyserReflector.createMoodAnalyser( constructor,"i am in happy mood" );
        Object analyseMood = MoodAnalyserReflector.invokeMethod( moodAnalyserObject, "analyseMood" );
        Assert.assertEquals( "happy",analyseMood );
    }

    @Test
    public void givenNullMessage_UsingReflection_ShouldReturnException() throws MoodAnalyserException {
        Constructor<?> constructor =  MoodAnalyserReflector.getConstructor(String.class);
        MoodAnalyser moodAnalyserObject = (MoodAnalyser) MoodAnalyserReflector.createMoodAnalyser( constructor,"sad" );

        try {
           Object analyseMood = MoodAnalyserReflector.invokeMethod( moodAnalyserObject, "analyseMood" );
            Assert.assertEquals( "sad",analyseMood );
        } catch (MoodAnalyserException e) {
            e.getCause().getCause().printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyser_OnChangeMood_ShouldReturnHappy() throws MoodAnalyserException {

        Constructor<?> constructor =  MoodAnalyserReflector.getConstructor(String.class);
        MoodAnalyser moodAnalyserObject = (MoodAnalyser) MoodAnalyserReflector.createMoodAnalyser( constructor,"" );
        MoodAnalyserReflector.setFieldValue(moodAnalyserObject,"message","i am in happy mood");
        Object analyseMood = MoodAnalyserReflector.invokeMethod( moodAnalyserObject, "analyseMood" );
        Assert.assertEquals( "happy",analyseMood );
    }

    @Test
    public void givenHappyMessage_withDefaultConstructor_ShouldReturnHappy() throws MoodAnalyserException {
        Constructor<?> constructor =  MoodAnalyserReflector.getConstructor();
        MoodAnalyser moodAnalyserObject = (MoodAnalyser) MoodAnalyserReflector.createMoodAnalyser( constructor );
        MoodAnalyserReflector.setFieldValue( moodAnalyserObject,"message","i am in happy mood" );
        Object analyseMood = MoodAnalyserReflector.invokeMethod( moodAnalyserObject, "analyseMood" );
        Assert.assertEquals( "happy",analyseMood );
    }

    @Test
    public void givenMoodAnalyser_withType_ShouldReturnHappy() throws MoodAnalyserException {
        Constructor<?> constructor = MoodAnalyserReflector.getConstructor( String.class, MoodAnalyser.MoodType.class );
        MoodAnalyser mood = (MoodAnalyser) MoodAnalyserReflector.createMoodAnalyser( constructor, "i am in happy mood", MoodAnalyser.MoodType.happy );
        Assert.assertEquals( new MoodAnalyser( "i am in happy mood" ),mood );
    }
}

