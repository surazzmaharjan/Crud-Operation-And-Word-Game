package com.example.a4thassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a4thassessment.database.DbHelper;
import com.example.a4thassessment.model.Student;

import java.util.List;

public class CrudOperationActivity extends AppCompatActivity {
    EditText editTextName,editTextEmail,editTextPhone;
    Button btnSave,btnallstudent;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_crud_operation);


        editTextName = findViewById(R.id.stname);
        editTextEmail = findViewById(R.id.stemail);
        editTextPhone = findViewById(R.id.stphone);

        btnSave = findViewById(R.id.btnStSave);
        btnallstudent = findViewById(R.id.btnAllStudent);


        btnallstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrudOperationActivity.this, AllStudentActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DbHelper(this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = editTextName.getText().toString();
                String e = editTextEmail.getText().toString();
                String p = editTextPhone.getText().toString();

                Student student = new Student(0,n,e,p);

                if(dbHelper.addStudents(student)){
                    Toast.makeText(CrudOperationActivity.this, "Successfully saved", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
