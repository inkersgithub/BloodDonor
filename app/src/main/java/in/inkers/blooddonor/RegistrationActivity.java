package in.inkers.blooddonor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sreekkutty on 29/10/17.
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name,email,phone,password,cpassword;
    private Spinner bloodgroup;
    private ProgressBar progressreg;
    private FirebaseAuth mAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name=findViewById(R.id.etregname);
        email=findViewById(R.id.etregemail);
        phone=findViewById(R.id.etregphone);
        password=findViewById(R.id.etregpassword);
        cpassword=findViewById(R.id.etregconfirmpassword);
        bloodgroup=findViewById(R.id.spinnerregbloodgrp);
        progressreg=findViewById(R.id.pbreg);

        findViewById(R.id.btreg).setOnClickListener(this);
        findViewById(R.id.tvreglogin).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    private void registerUser(){
        final String inName=name.getText().toString().trim();
        final String inEmail=email.getText().toString().trim();
        final String inPhone=phone.getText().toString().trim();
        final String inPassword=password.getText().toString().trim();
        final String inCPassword=cpassword.getText().toString().trim();
        final String inBloodGroup=String.valueOf(bloodgroup.getSelectedItem());

        if(inName.isEmpty()){
            name.setError("Name is required");
            name.requestFocus();
            return;
        }
        if(inEmail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(inEmail).matches()){
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        }
        if(inPhone.isEmpty()){
            phone.setError("Phone number is required");
            phone.requestFocus();
            return;
        }
        if(inPhone.length()!=10){
            phone.setError("Enter a valid phone number");
            phone.requestFocus();
            return;
        }
        if(inPassword.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(inPassword.length()<6){
            password.setError("Min. length of password is 6 characters");
            password.requestFocus();
            return;
        }
        if(inCPassword.isEmpty()){
            cpassword.setError("Please confirm your password");
            cpassword.requestFocus();
            return;
        }
        if(!inPassword.equals(inCPassword)){
            cpassword.setError("Password should be same as entered above");
            cpassword.requestFocus();
            return;
        }
        if(inBloodGroup.equals("Select your Blood Group")){
            Toast.makeText(this,"Please select your blood group",Toast.LENGTH_SHORT).show();
            bloodgroup.requestFocus();
            return;
        }

        progressreg.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(inEmail,inPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressreg.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    mAuth.signInWithEmailAndPassword(inEmail,inPassword);
                    saveUserInfo(inName,inEmail,inPhone,inBloodGroup);
                    Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //Back button won't take back to login again
                    startActivity(intent);
                    finish();
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registered, Please login",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void saveUserInfo(String name,String email,String phone,String blood){
        UserInformation userInformation=new UserInformation(name,email,phone,blood);

        FirebaseUser user= mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btreg:
                registerUser();
                break;

            case R.id.tvreglogin:
                finish();
                break;
        }
    }
}
