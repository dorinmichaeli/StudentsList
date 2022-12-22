package com.example.studentlistapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {

    private static final Model _instance = new Model();

    public static Model instance(){
        return _instance;
    }

    List<Student> data = new LinkedList<>();
    public List<Student> getAllStudents(){
        return data;
    }
    public void addStudent(Student s){
        data.add(s);
    }
}
