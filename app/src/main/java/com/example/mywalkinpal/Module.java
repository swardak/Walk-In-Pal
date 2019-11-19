package com.example.mywalkinpal;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module extends Application {
    public ArrayList<String> servList = new ArrayList<>();
    public ArrayAdapter<String> servAdapter;
    public String service;
    public String role;

    public Module(){

    }

    public Module(String service, String role){
        this.service = service;
        this.role = role;
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
}
