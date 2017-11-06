package in.inkers.blooddonor;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth=FirebaseAuth.getInstance();

        Thread myThread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);

                    if(firebaseAuth.getCurrentUser()==null){
                        intent=new Intent(getApplicationContext(),LoginActivity.class);
                    }
                    else{
                        intent=new Intent(getApplicationContext(),MainActivity.class);
                    }
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
    }
}
