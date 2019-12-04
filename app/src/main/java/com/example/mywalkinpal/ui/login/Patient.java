package com.example.mywalkinpal.ui.login;

import com.example.mywalkinpal.Rate;
import com.example.mywalkinpal.Service;
import com.example.mywalkinpal.ui.login.UserProfile;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Patient extends UserProfile {

    //private ArrayList<Booking> = new ArrayList<>;

    public Patient(){

    }
    public Patient(String userFirstName, String userLastName, String userEmail, String userType, String hashedPass){
        super( userFirstName, userLastName, userEmail, userType, hashedPass);
    }

    //possibly get bookings? list of bookings stored here?
    //list of clinics with which this patient has bookings in

}
