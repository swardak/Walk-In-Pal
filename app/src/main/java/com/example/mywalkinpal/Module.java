package com.example.mywalkinpal;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module extends Application {
    private ArrayList<String> servList = new ArrayList<>();
    private ArrayAdapter<String> servAdapter;
    private String service;
    private String role;

    private String rate;

    public Module(){

    }

    public Module(String service, String role, String rate){
        this.service = service;
        this.role = role;
        this.rate = rate;
    }
    public String getService(){
        return service;
    }
    public String getRole(){
        return role;
    }
    public void setService(String name){
        service = name;
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
