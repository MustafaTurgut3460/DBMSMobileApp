package com.example.vtmobileapp.Models;

public class Device {
    private String name, status;
    private int id, stationId;

    public Device(String name, String status, int id, int stationId) {
        this.name = name;
        this.status = status;
        this.id = id;
        this.stationId = stationId;
    }

    public Device() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
}
