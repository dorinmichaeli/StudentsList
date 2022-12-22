package com.example.studentlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentlistapp.model.Model;
import com.example.studentlistapp.model.Student;
import com.example.studentslist.R;

public class EditStudentActivity extends AppCompatActivity {
    int pos;
    Student s;
    EditText name;
    EditText id;
    EditText phone;
    EditText address;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        pos = (int) getIntent().getSerializableExtra("key");
        s = Model.instance().getAllStudents().get(pos);
        name = findViewById(R.id.edit_student_editText_name);
        id = findViewById(R.id.edit_student_editText_id);
        phone = findViewById(R.id.edit_student_editText_phone);
        address = findViewById(R.id.edit_student_editText_address);
        cb = findViewById(R.id.edit_student_checkBox);
        setTheCurrentDetails();
        Button cancel = findViewById(R.id.edit_student_btnCancel);
        cancel.setOnClickListener(view -> {
            Intent intent = new Intent(this, StudentsListActivity.class);
            startActivity(intent);
        });
        Button delete = findViewById(R.id.edit_student_btnDelete);
        delete.setOnClickListener(view -> {
            Model.instance().getAllStudents().remove(pos);
            Intent intent = new Intent(this, StudentsListActivity.class);
            startActivity(intent);
        });
        Button save = findViewById(R.id.edit_student_btnSave);
        save.setOnClickListener(view -> {
            String name = this.name.getText().toString();
            String id = this.id.getText().toString();
            String phone = this.phone.getText().toString();
            String address = this.address.getText().toString();
            Boolean cb = this.cb.isChecked();
            Model.instance().getAllStudents().remove(pos);
            Model.instance().addStudent(new Student(name, id, phone, address, cb));
            Intent intent = new Intent(this, StudentsListActivity.class);
            startActivity(intent);
        });
    }

    public void setTheCurrentDetails(){
        this.name.setText(this.s.getName());
        this.id.setText(this.s.getId());
        this.phone.setText(this.s.getPhone());
        this.address.setText(this.s.getAddress());
        this.cb.setChecked(this.s.isCb());
    }
}