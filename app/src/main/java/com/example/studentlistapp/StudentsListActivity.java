package com.example.studentlistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentlistapp.model.Model;
import com.example.studentlistapp.model.Student;
import com.example.studentslist.R;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    List<Student> data;
    StudentRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        data = Model.instance().getAllStudents();
        RecyclerView rl = findViewById(R.id.recycleList);
        rl.setHasFixedSize(true);
        rl.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentRecyclerAdapter();
        rl.setAdapter(adapter);

        Button plusBtn = findViewById(R.id.btnPlus);
        plusBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });

        adapter.setOnClickListener((int pos)-> {
            Log.d("TAG", "Row was clicked " + pos);
            Intent intent = new Intent(this,StudentDetailsActivity.class);
            intent.putExtra("key",pos);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }


    class  StudentViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView id;
        CheckBox cb;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            id = itemView.findViewById(R.id.txtId);
            cb = itemView.findViewById(R.id.cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int)cb.getTag();
                    Student s = data.get(pos);
                    s.cb = cb.isChecked();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }

        public void bind(Student s, int pos) {
            name.setText(s.getName());
            id.setText(s.getId());
            cb.setChecked(s.cb);
            cb.setTag(pos);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{
        OnItemClickListener listener;
        void setOnClickListener(OnItemClickListener l){
            this.listener = l;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student s = data.get(position);
            holder.bind(s, position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}