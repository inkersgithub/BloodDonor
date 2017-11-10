package in.inkers.blooddonor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        findViewById(R.id.tvlogout).setOnClickListener(this);

        if(firebaseAuth.getCurrentUser()==null){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.tvlogout:
                firebaseAuth.signOut();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
        }
    }

    public void searchClick(MenuItem menuItem){
        startActivity(new Intent(this,BloodGroupActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
