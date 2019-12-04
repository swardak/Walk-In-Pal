package com.example.mywalkinpal;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mywalkinpal.ui.login.Patient;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Queue;

public class BookingQueue extends Application {
    private String date;

    public ArrayDeque<Patient> patientQueue = new ArrayDeque<>();

    public BookingQueue(){

    }

    public BookingQueue(Patient patient){
        patientQueue.add(patient);
    }

    public int size(){
        return patientQueue.size();
    }

    public double apptTime(){
        return 4;
        //first, check which day of the week the appointment has been scheduled
        //convert that to an int (0-6)
        //call workingHours to see the working hours of the current day
        //then call waitTime to see how long the current wait time is
        //divide that by 60 and then add it to start hour (query this too)
        //convert this into an actual time
        //result is the time of the appointment

        //MAX number of appointments allowed that day:
        //if doing this, then size to check current appointments and then somehow convert that to a time
        //using the 15 min increments
    }

    private double workingHours(int day){
        return 4;

        //query the database to get the working hours of that day (days are from 0-6)
        //then query both 0 and 1 of that day, save them in a local variable, then subtract them to get hours in the day and save that
        //that variable is the working hours of that day

        //OR possbly max number of appts allowed that day instead
    }

    public double waitTime(){
        return patientQueue.size() * 15;
    }

    public void dequeuePatient(){ //figure out how to make this work
        while (!patientQueue.isEmpty()) {
            final Handler handler = new Handler();
            final int delay = 900000; //milliseconds

            handler.postDelayed(new Runnable() {
                public void run() {
                    patientQueue.removeFirst();
                    handler.postDelayed(this, delay);
                }
            }, delay);
        }
    }

    public void addPatient(Patient patient){
        patientQueue.add(patient);
    }

    public String getDate(){
        return date;
    }




}
