package com.example.onlinerehab;

public class addict {
    public String uid;
    public String phone;
    public String dob;
    public String drug;
    public String other_drugs;
    public String duration_used;
    public String location;
    public String gender;

    public addict(){
    }

    public addict(String uid, String phone, String dob, String drug, String other_drugs, String duration_used, String location, String gender) {
        this.uid = uid;
        this.phone = phone;
        this.dob = dob;
        this.drug = drug;
        this.other_drugs = other_drugs;
        this.duration_used = duration_used;
        this.location = location;
        this.gender = gender;
    }

    public String getUid() {
        return uid;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getDrug() {
        return drug;
    }

    public String getOther_drugs() {
        return other_drugs;
    }

    public String getDuration_used() {
        return duration_used;
    }

    public String getLocation() {
        return location;
    }

    public String getGender() {
        return gender;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public void setOther_drugs(String other_drugs) {
        this.other_drugs = other_drugs;
    }

    public void setDuration_used(String duration_used) {
        this.duration_used = duration_used;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}



