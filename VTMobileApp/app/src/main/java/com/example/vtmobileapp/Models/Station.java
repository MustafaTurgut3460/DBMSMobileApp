package com.example.vtmobileapp.Models;

public class Station {
    private int id;
    private String name, ilceId, address;

    public Station(String name, String ilceId, String address) {
        this.name = name;
        this.ilceId = ilceId;
        this.address = address;
    }

    public Station() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIlceId() {
        return ilceId;
    }

    public void setIlceId(String ilceId) {
        this.ilceId = ilceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
