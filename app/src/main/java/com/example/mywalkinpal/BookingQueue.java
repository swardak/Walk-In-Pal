package com.example.mywalkinpal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mywalkinpal.ui.login.Patient;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class BookingQueue extends Application {
    public ArrayDeque<Patient> patientQueue = new ArrayDeque<>();
    //public ArrayAdapter<String> servAdapter; is this necessary

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
    }

    public double waitTime(){
        return patientQueue.size() * 15;
    }



}
