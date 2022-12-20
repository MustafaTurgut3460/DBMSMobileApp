package com.example.vtmobileapp.Models;

public class Service {
    private int id, employeeId, deviceId;
    private String deviceStatus, time, serviceTitle, serviceDesc;

    public Service(int employeeId, int deviceId, String deviceStatus, String time, String serviceTitle, String serviceDesc) {
        this.employeeId = employeeId;
        this.deviceId = deviceId;
        this.deviceStatus = deviceStatus;
        this.time = time;
        this.serviceTitle = serviceTitle;
        this.serviceDesc = serviceDesc;
    }

    public Service() {
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
