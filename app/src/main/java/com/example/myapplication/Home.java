package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupUIViews();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            final String userID = user.getUid();
            if (user != null)
            {
                mDatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(userID))
                        {
                            Intent moveToHome = new Intent(Home.this,ProfileActivity.class);
                            moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(moveToHome);
                        }
                        else {
                            Intent moveToHome = new Intent(Home.this,ProfileActivity.class);
                            moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(moveToHome);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }else{
                Intent moveToHome = new Intent(Home.this,login.class);
                moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveToHome);
                finish();
            }
        };
        mAuth.addAuthStateListener(mAuthListener);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
    private void setupUIViews()
    {

    }
}