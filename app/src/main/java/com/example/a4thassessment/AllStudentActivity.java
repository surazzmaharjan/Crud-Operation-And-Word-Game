package com.example.a4thassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.a4thassessment.database.DbHelper;
import com.example.a4thassessment.model.Student;

import java.util.ArrayList;
import java.util.List;

public class AllStudentActivity extends AppCompatActivity {


    public static   RecyclerView recyclerview;
    List<Student> editstudent= new ArrayList<>();
    DbHelper dbHelper;
    RecyclerView.LayoutManager layoutManager;
    public static SearchStudentAdapter searchStudentAdapter;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_all_student);

        recyclerview = findViewById(R.id.student_edit);
        btnHome = findViewById(R.id.btnHome);

        dbHelper = new DbHelper(this);
        editstudent = dbHelper.getStudents();



        searchStudentAdapter = new SearchStudentAdapter(editstudent,this);
        layoutManager= new LinearLayoutManager(this);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(searchStudentAdapter);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.btnHome)
                {
                    Intent intent = new Intent(AllStudentActivity.this,CrudOperationActivity.class);
                    startActivity(intent);
                }
            }
        });



    }



}