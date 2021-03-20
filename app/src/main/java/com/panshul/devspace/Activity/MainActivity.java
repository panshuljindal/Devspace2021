package com.panshul.devspace.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.panshul.devspace.Fragments.ClockFragment;
import com.panshul.devspace.Fragments.FriendsFragment;
import com.panshul.devspace.Fragments.ListFragment;
import com.panshul.devspace.Fragments.MusicFragment;
import com.panshul.devspace.R;
import com.panshul.devspace.Fragments.TaskFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FirebaseAuth mauth;
    DatabaseReference myref;
    SharedPreferences.Editor editor;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth=FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        myref = db.getReference("Users");
        SharedPreferences pref = getSharedPreferences("com.panshul.devspace.userdata",MODE_PRIVATE);
        editor = pref.edit();


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
    public void datasave(){
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseUser user = mauth.getCurrentUser();
                uid = user.getUid();

                editor.putString("uid",uid);
                String emaili = snapshot.child(uid).child("email").getValue().toString();
                editor.putString("emailId", emaili);

                String fcm = snapshot.child(uid).child("fcm").getValue().toString();
                editor.putString("fcm", fcm);

                String name = snapshot.child(uid).child("name").getValue().toString();
                editor.putString("name", name);

                String phone = snapshot.child(uid).child("phoneNumber").getValue().toString();
                editor.putString("phone", phone);

                String points = snapshot.child(uid).child("points").getValue().toString();
                editor.putString("points", points);

                editor.apply();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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