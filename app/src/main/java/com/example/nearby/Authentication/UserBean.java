package com.example.nearby.Authentication;

public class UserBean {
    public String fname,lname,email,dob,phone,address,pin,gender,id;

    public UserBean(){

    }
    public UserBean(String fname, String lname, String email, String dob, String phone, String address, String pin,String gender, String id) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.pin = pin;
        this.gender=gender;
        this.id=id;
    }
}
