package com.example.onlinerehab;

public class psy {
    public String psy_name;
    public String psy_phone;
    public String psy_speciality;

    public psy(String psy_name, String psy_phone, String psy_speciality) {
        this.psy_name = psy_name;
        this.psy_phone = psy_phone;
        this.psy_speciality = psy_speciality;
    }

    public String getPsy_name() {
        return psy_name;
    }

    public void setPsy_name(String psy_name) {
        this.psy_name = psy_name;
    }

    public String getPsy_phone() {
        return psy_phone;
    }

    public void setPsy_phone(String psy_phone) {
        this.psy_phone = psy_phone;
    }

    public String getPsy_speciality() {
        return psy_speciality;
    }

    public void setPsy_speciality(String psy_speciality) {
        this.psy_speciality = psy_speciality;
    }
}
