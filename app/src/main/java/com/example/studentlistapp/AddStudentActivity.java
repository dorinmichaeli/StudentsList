package com.example.studentlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.studentlistapp.model.Model;
import com.example.studentlistapp.model.Student;
import com.example.studentslist.R;

import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        ImageView img = findViewById(R.id.add_student_img);
        EditText nameET = findViewById(R.id.add_student_editText_name);
        EditText idET = findViewById(R.id.add_student_editText_id);
        EditText phoneET = findViewById(R.id.add_student_editText_phone);
        EditText addressET = findViewById(R.id.add_student_editText_address);
        CheckBox checkCB = findViewById(R.id.add_student_checkBox);
        Button save = findViewById(R.id.add_student_btnSave);
        Button cancel = findViewById(R.id.add_student_btnCancel);
        save.setOnClickListener(view -> {
            String name = nameET.getText().toString();
            String id = idET.getText().toString();
            String phone = phoneET.getText().toString();
            String address = addressET.getText().toString();
            Boolean cb = checkCB.isChecked();

            Model.instance().addStudent(new Student(name, id, phone, address, cb));
            finish();
        });
        cancel.setOnClickListener(view -> {
            Intent intent = new Intent(this, StudentsListActivity.class);
            startActivity(intent);
        });
    }
}