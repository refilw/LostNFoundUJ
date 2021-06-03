package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Upload extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private EditText mSearchField;
    private Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        setupUIViews();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveHistory();
            }
        });
    }

    void saveHistory(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        myRef.child("Users").setValue(mSearchField);
        hideSoftKeyboard();
    }

    private void setupUIViews()
    {
        mSearchField = (EditText)
                findViewById(R.id.editTextTextPersonName3);
        upload = (Button)findViewById(R.id.button);
    }

    private void hideSoftKeyboard()
    {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}