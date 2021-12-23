package com.example.nearby.Client;

public class RequestBean {
    public String serviceRequest;
    public String desc;
    public String diff;
    public String date;
    public String service;
    public long timestamp;
    public int pin;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }



    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public RequestBean() {
    }

    public String getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(String serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public RequestBean(String serviceRequest, String desc, String diff, String date, long timestamp,int pin,String service) {
        this.serviceRequest = serviceRequest;
        this.desc = desc;
        this.diff = diff;
        this.date=date;
        this.timestamp=timestamp;
        this.pin=pin;
        this.service=service;
    }
}
