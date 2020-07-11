package com.taofeek.bloodcrossmatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class RegistrationActivity extends AppCompatActivity {
    EditText email_reg;
    EditText password_reg;
    EditText confirm_password_reg;
    Button register_button;
    private String mEmail;
    private String mPassword;
    private String mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email_reg = findViewById(R.id.reg_email);
        password_reg = findViewById(R.id.reg_password);
        confirm_password_reg = findViewById(R.id.reg_confirm_password);
        register_button = findViewById(R.id.reg_button);
        mEmail = getText(email_reg);
        mPassword = getText(password_reg);
        mConfirmPassword = getText(confirm_password_reg);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
    public void registrationProcess (){
        if (mPassword != null && mEmail != null && mConfirmPassword != null){
            if (mPassword == mConfirmPassword){
                DataManager dm =new DataManager();
                dm.insertDataRegister(mEmail,mPassword);
            }
            else {
                Snackbar.make(findViewById(R.id.reg_view),"Password field do not match confirm password field", Snackbar.LENGTH_LONG).show();
            }


        }
    }
    public String getText(EditText editText){
        String text = editText.getText().toString();
        return text;
    }
    public  void resetEditText(){
        email_reg.setText("");
        password_reg.setText("");
        confirm_password_reg.setText("");

    }
}
