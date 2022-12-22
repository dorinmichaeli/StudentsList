package com.example.studentlistapp.model;

public class Student {
    String name;
    String id;
    String phone;
    String address;
    public boolean cb;

    public Student(String name, String id, String phone, String address, boolean cb) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.cb = cb;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public boolean isCb() {
        return cb;
    }
}
