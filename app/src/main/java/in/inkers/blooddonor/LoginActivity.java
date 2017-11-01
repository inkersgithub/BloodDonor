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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by sreekkutty on 31/10/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    ProgressBar progressLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.etloginemail);
        password=findViewById(R.id.etloginpassword);
        progressLogin=findViewById(R.id.pblogin);

        findViewById(R.id.tvnotuser).setOnClickListener(this);
        findViewById(R.id.btlogin).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    private void loginUser(){
        String inEmail=email.getText().toString().trim();
        String inPassword=password.getText().toString().trim();

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
        if(inPassword.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        progressLogin.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(inEmail,inPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressLogin.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //Back button won't take back to login again
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tvnotuser:
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
                break;

            case R.id.btlogin:
                loginUser();
                break;
        }
    }
}
