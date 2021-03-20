package com.panshul.devspace.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.panshul.devspace.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPage extends AppCompatActivity {

    EditText name,emailId,phoneNumber,password;
    ImageView passwordView;
    Button signUp;
    DatabaseReference myref;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        name = findViewById(R.id.signUpName);
        emailId = findViewById(R.id.signUpEmail);
        phoneNumber = findViewById(R.id.signUpMobileNumber);
        password = findViewById(R.id.signUpPassword);
        signUp = findViewById(R.id.signUpButton);
        mauth = FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        myref = db.getReference("Users");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(v.getContext())){
                    if (checkEmpty()){
                        if(checkMail()){
                            if (checkPhone()){
                                mauth.createUserWithEmailAndPassword(emailId.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            Intent intent = new Intent(SignUpPage.this,LoginPage.class);
                                            FirebaseUser user = mauth.getCurrentUser();
                                            String uid = user.getUid();
                                            myref.child(uid).child("name").setValue(name.getText().toString());
                                            myref.child(uid).child("emailId").setValue(emailId.getText().toString());
                                            myref.child(uid).child("phoneNumber").setValue(phoneNumber.getText().toString());
                                            myref.child(uid).child("uid").setValue(uid);
                                            myref.child(uid).child("fcm").setValue("null");
                                            myref.child(uid).child("points").setValue("0");
                                            startActivity(intent);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
                else {
                    Toast.makeText(SignUpPage.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    boolean checkEmpty(){
        if(name.getText().length()==0){
            Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(emailId.getText().length()==0){
            Toast.makeText(this, "Please enter Email ID", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phoneNumber.getText().length()==0){
            Toast.makeText(this, "Please enter a Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().length()==0){
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    boolean checkPhone(){
        String tempEmail = phoneNumber.getText().toString();
        if(tempEmail.startsWith("6")  && tempEmail.length()==10){
            return true;
        }
        if(tempEmail.startsWith("7")  && tempEmail.length()==10){
            return true;
        }
        if(tempEmail.startsWith("8")  && tempEmail.length()==10){
            return true;
        }
        if(tempEmail.startsWith("9")  && tempEmail.length()==10){
            return true;
        }
        Toast.makeText(this, "Please enter a correct Phone Number", Toast.LENGTH_SHORT).show();
        return false;
    }
    boolean checkMail(){
        String tempEmail=emailId.getText().toString().trim();
        Pattern emailPattern=Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        Matcher emailMatcher=emailPattern.matcher(tempEmail);
        if(emailMatcher.matches())
        {
            return true;
        }
        Toast.makeText(SignUpPage.this, "Please enter Vit Email ID", Toast.LENGTH_SHORT).show();
        return false;
    }

    boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}