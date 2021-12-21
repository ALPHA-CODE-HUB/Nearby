package com.example.nearby.Client;

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

import com.example.nearby.R;
import com.example.nearby.ServiceProvider.Serviceprovider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClientHomePage extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    Switch s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        }



s=findViewById(R.id.sw);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 if(s.isChecked())
                 {
                     Intent intent=new Intent(ClientHomePage.this, Serviceprovider.class);
                     startActivity(intent);
                     return;

                 }
            }
        });

        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new categoryFragment()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.request:
                    fragment = new requestFragment();
                    break;
                case R.id.category:
                    fragment = new categoryFragment();
                    break;
                case R.id.chat:
                    fragment = new chatFragment();
                    break;
                case R.id.account:
                    fragment = new accountFragment();
                    break;


            }
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment).commit();

            return true;
        }
    };
}