package com.example.mywalkinpal.ui.login;

public class UserProfile {
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userType;
    private String hashedPass;

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

}
