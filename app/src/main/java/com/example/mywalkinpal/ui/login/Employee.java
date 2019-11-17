package com.example.mywalkinpal.ui.login;

import com.example.mywalkinpal.Service;

import java.util.ArrayList;

public class Employee extends UserProfile {

    public String address;
    public String phoneNumber;
    public String clinicName;
    public ArrayList<Service> services;
    public ArrayList<String> insuranceTypesAccepted;
    public ArrayList<String> paymentTypesAccepted;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public ArrayList<String> getInsuranceTypesAccepted() {
        return insuranceTypesAccepted;
    }

    public void setInsuranceTypesAccepted(ArrayList<String> insuranceTypesAccepted) {
        this.insuranceTypesAccepted = insuranceTypesAccepted;
    }

    public ArrayList<String> getPaymentTypesAccepted() {
        return paymentTypesAccepted;
    }

    public void setPaymentTypesAccepted(ArrayList<String> paymentTypesAccepted) {
        this.paymentTypesAccepted = paymentTypesAccepted;
    }



    public Employee(){

    }
    public Employee(String userFirstName, String userLastName, String userEmail, String userType,String hashedPass, String address, String phoneNumber, String clinicName, ArrayList<String> insuranceTypesAccepted,ArrayList<String> paymentTypesAccepted, ArrayList<Service> services ){
        super( userFirstName, userLastName, userEmail, userType, hashedPass);
        this.address = address;
        this.paymentTypesAccepted = paymentTypesAccepted;
        this.phoneNumber = phoneNumber;
        this.clinicName = clinicName;
        this.services = services;
    }
}
