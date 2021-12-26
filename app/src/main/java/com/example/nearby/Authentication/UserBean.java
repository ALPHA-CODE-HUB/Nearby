package com.example.nearby.Authentication;

public class UserBean {
    public String fname, lname, email, dob, phone, address, pin, gender, id;

    public UserBean() {

    }

    public UserBean(String fname, String lname, String email, String dob, String phone, String address, String pin, String gender, String id) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.pin = pin;
        this.gender = gender;
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
