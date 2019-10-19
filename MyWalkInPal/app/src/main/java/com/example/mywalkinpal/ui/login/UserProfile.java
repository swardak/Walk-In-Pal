package com.example.mywalkinpal.ui.login;

public class UserProfile {
    public String userEmail;
    public String userFirstName;
    public String userLastName;
    public String userType;

    public  UserProfile( ){

    }


    public UserProfile(String userFirstName, String userLastName, String userEmail, String userType) {
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userType = userType;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }


    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserLasttName() {
        return userLastName;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    public String getUserType(){
        return userType;
    }

}
