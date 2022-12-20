package com.example.vtmobileapp.Models;

public class Employee {
    private String nameSurname, email, password, phone, status;
    private int id;

    public Employee(String nameSurname, String email, String password, String phone, String status) {
        this.nameSurname = nameSurname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = status;
    }

    public Employee() {
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
