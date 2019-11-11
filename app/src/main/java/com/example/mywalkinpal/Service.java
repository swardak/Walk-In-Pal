package com.example.mywalkinpal;

public class Service {

    public String name;
    public String role;

    public Service(){

    }
    public Service(String name, String role){
        this.name = name;
        this.role = role;
    }

    public String getName(){
        return this.name;
    }
    public String getRole(){
        return this.role;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setRole(String role){
        this.role = role;
    }
    @Override
    public String toString(){
        return "Service: " + this.name + "\nRole: " + this.role;
    }
}
