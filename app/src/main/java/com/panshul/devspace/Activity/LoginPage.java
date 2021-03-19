package com.panshul.devspace.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.panshul.devspace.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends AppCompatActivity {

    EditText password,email;
    Button login;
    String passwordId,emailId;
    FirebaseAuth mauth;
    TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        password = findViewById(R.id.editTextTextPersonLoginPwd);
        email = findViewById(R.id.editTextTextLoginName);
        login = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupTextView);
        mauth = FirebaseAuth.getInstance();
        if (mauth.getCurrentUser()!=null){

        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,SignUpPage.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkempty()){
                    if(checkemail()){
                        emailId = email.getText().toString();
                        passwordId = password.getText().toString();
                        mauth.signInWithEmailAndPassword(emailId,passwordId).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    datasave();
                                    Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                                else {
                                    email.setText("");
                                    password.setText("");
                                    Toast.makeText(LoginPage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });


    }
    public void datasave(){

    }
    public boolean checkempty(){
        if(email.getText().length()==0){
            Toast.makeText(this, "Please enter a Email Id", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if(password.getText().length()==0){
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
    public boolean checkemail(){
        String tempemail=email.getText().toString().trim();
        Pattern emailpattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        Matcher emailMatcher= emailpattern.matcher(tempemail);
        if(emailMatcher.matches()){
            return true;
        }
        email.setError("Please enter a valid email id");
        email.requestFocus();
        return false;
    }
}