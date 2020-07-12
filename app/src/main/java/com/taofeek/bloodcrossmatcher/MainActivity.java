package com.taofeek.bloodcrossmatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;
    TextView signUpTextView;
    public String mLoginemail;
    public String mLoginpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenHelper openHelper = new OpenHelper(MainActivity.this);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signUpTextView = findViewById(R.id.signup_textview);
        mLoginemail =  getText(loginEmail);
        mLoginpassword = getText(loginPassword);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logCheck();

            }
        });
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regintent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(regintent);
            }
        });

    }
    public String getText(EditText editText){
        String text = editText.getText().toString();
        return text;
    }
    public void logCheck() {
        DataManager dm =new DataManager();
        Boolean checkstat = dm.loginCheck(mLoginemail,mLoginpassword);
        if (checkstat){
            Intent logintent = new Intent(MainActivity.this,Profile.class);
            startActivity(logintent);
        }
        else {
            Snackbar.make(findViewById(R.id.logview),"Password field do not match confirm password field", Snackbar.LENGTH_LONG).show();
        }
    }




}
