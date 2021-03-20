package com.panshul.devspace.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.panshul.devspace.Fragments.ClockFragment;
import com.panshul.devspace.Fragments.FriendsFragment;
import com.panshul.devspace.Fragments.ListFragment;
import com.panshul.devspace.Fragments.MusicFragment;
import com.panshul.devspace.R;
import com.panshul.devspace.Fragments.TaskFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ListFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedfragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_list:
                        selectedfragment = new ListFragment();
                        break;
                    case R.id.navigation_clock:
                        selectedfragment = new ClockFragment();
                        break;
                    case R.id.navigation_music:
                        selectedfragment = new MusicFragment();
                        break;
                    case R.id.navigation_friends:
                        selectedfragment = new FriendsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedfragment).commit();
                return  true;
            }
        });
    }
    Boolean doubleback=false;
    @Override
    public void onBackPressed() {
        if(doubleback) {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } else {
            doubleback = true;

            Toast.makeText(this, "Please once again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleback = false;
                }
            }, 2000);
        }
    }

}