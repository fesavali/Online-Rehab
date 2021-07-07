package com.example.onlinerehab;

public class UserDetails {
    public String name;
    public String speciality;

    public UserDetails(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }
    public String toString(){
        return this.name + ": " + speciality;
    }
    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}