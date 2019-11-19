package com.example.mywalkinpal.ui.login;

import androidx.annotation.NonNull;

public class UserProfile {
    public String userEmail;
    public String userFirstName;
    public String userLastName;
    public String userType;
    public String hashedPass;

    public  UserProfile(){

    }


    public UserProfile(String userFirstName, String userLastName, String userEmail, String userType,String hashedPass) {
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userType = userType;
        this.hashedPass = hashedPass;
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

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    public String getUserType(){
        return userType;
    }

    public String getHashedPass(){return hashedPass;}

    public void setHashedPass(String pass){this.hashedPass = pass;}

    @NonNull
    @Override
    public String toString() {
        return "First Name: " + this.userFirstName + "\nLast Name: " + this.userLastName + "\nE-mail:" + this.userEmail;

    }
}
