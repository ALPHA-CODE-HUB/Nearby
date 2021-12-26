package com.example.nearby.ServiceProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.nearby.Client.ClientHomePage;
import com.example.nearby.Client.categoryFragment;
import com.example.nearby.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Serviceprovider extends AppCompatActivity {
    Switch s1;
    private BottomNavigationView bottomNavigationView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_serviceprovider);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        }
        s1 = findViewById(R.id.sw1);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (s1.isChecked()) {


                } else {
                    Intent intent = new Intent(Serviceprovider.this, ClientHomePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.nav_default_pop_enter_anim,R.anim.nav_default_pop_exit_anim);
                    finish();
                    return;
                }
            }
        });

        bottomNavigationView1 = findViewById(R.id.nav1);
        bottomNavigationView1.setOnNavigationItemSelectedListener(bottomnav);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, new RequestFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomnav=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.request1:
                    fragment = new RequestFragment();
                    break;
                case R.id.review1:
                    fragment = new ReviewFragment();
                    break;
                case R.id.chat1:
                    fragment = new ChatFragment();
                    break;
                case R.id.account1:
                    fragment = new AccountFragment();
                    break;


            }
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1,fragment).commit();

            return true;
        }
    };

}