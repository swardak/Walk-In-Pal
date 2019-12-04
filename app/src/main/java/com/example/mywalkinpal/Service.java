package com.example.mywalkinpal;

public class Service {

    public String name;
    public String role;



    private String rate;

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString(){
        return "Service: " + this.name + "\nRole: " + this.role;
    }
}
