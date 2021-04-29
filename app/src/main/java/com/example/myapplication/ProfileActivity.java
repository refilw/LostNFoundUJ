package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfileActivity extends AppCompatActivity {
    EditText FName,LName,Height,Weight;
    Button saveP;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    //Firebase database fields
    DatabaseReference mUserDatabase;
    StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupUIViews();
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent moveToHome = new Intent(ProfileActivity.this,Home.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(moveToHome);
                }
            }
        };
        saveP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });
        //Firebase database instance
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }
    private boolean validateForm()
    {
        boolean valid = true;

        String FN = FName.getText().toString();
        if (TextUtils.isEmpty(FN)) {
            FName.setError("First Name is Required.");
            valid = false;
        } else {
            FName.setError(null);
        }
        String LN = LName.getText().toString();
        if (TextUtils.isEmpty(LN)) {
            LName.setError("Last Name is Required.");
            valid = false;
        } else {
            LName.setError(null);
        }
        return valid;
    }
    private void saveUserProfile()
    {
        String weih= Weight.getText().toString().trim();
        String name = FName.getText().toString().trim();
        String  surname = LName.getText().toString().trim();
        String heig = Height.getText().toString().trim();
        // validateForm();
//       if (TextUtils.isEmpty(name)){
//            Toast.makeText(this,"Please enter your First Name",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(surname)){
//            Toast.makeText(this,"Please enter your Last Name",Toast.LENGTH_SHORT).show();
//            return;
//        }

        if(validateForm()){
            progressDialog.setTitle("Saving to a database.......");
            progressDialog.setMessage("Please wait");
            progressDialog.show();
            //saving to a database
            mUserDatabase.child("First Name").setValue("Jack");
            mUserDatabase.child("Last Name").setValue("Kai");
            mUserDatabase.child("Userid").setValue(mAuth.getCurrentUser().getUid());

            startActivity(new Intent(ProfileActivity.this,Home.class));
        }

    }

    private void setupUIViews()
    {
        FName = (EditText)findViewById(R.id.txtName) ;
        LName = (EditText)findViewById(R.id.txtSurname) ;
        saveP = (Button)findViewById(R.id.btnSavebv);
    }
}
