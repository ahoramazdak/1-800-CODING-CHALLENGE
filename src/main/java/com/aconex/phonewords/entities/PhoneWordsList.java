/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author amin
 */
public class PhoneWordsList  {

    public static long getStartTime() {
        return startTime;
    }

    public static void setStartTime(long aStartTime) {
        startTime = aStartTime;
    }

    public static long getTimeToRun() {
        return lTimeToRun;
    }

    public static void setTimeToRun(long alTimeToRun) {
        lTimeToRun = alTimeToRun;
    }
    private final Collection<String> phoneWords;
    private boolean timeOut;
    private static long startTime;
    private static long lTimeToRun;
    public PhoneWordsList() {
        phoneWords=new HashSet<>();
        timeOut=false;
        
    }

    public long getRunTime(){
        return TimeUnit.MILLISECONDS.convert(System.currentTimeMillis()-startTime, TimeUnit.MILLISECONDS);
    }
    public boolean isTimeOut() {
        if(getRunTime()>lTimeToRun) {
            setTimeOut();
        }
        return timeOut;
    }

    public void setTimeOut() {
        this.timeOut = true;
    }
    public void add(String word){
        phoneWords.add(word);
    }
    public void addAll(PhoneWordsList words){
        phoneWords.addAll(words.getPhoneWordsList());
        timeOut=words.isTimeOut();
    }
    public Collection<String> getPhoneWordsList(){
        return Collections.unmodifiableCollection(phoneWords);
    }
    @Override
    public String toString() {
        return "PhoneWordsList{" + "phoneWords size=" + phoneWords.size() + " generated Completely("+!timeOut+") in "+getRunTime()+" miliseconds }\n";
    }

    public boolean isEmpty() {
        return phoneWords.isEmpty();
    }
    
           
}
