package com.taofeek.bloodcrossmatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class EditProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinner;
    private Button mButton;
    private EditText mName;
    private EditText mEmail;
    private EditText mNumber;
    private EditText mAge;
    private RadioButton mMale;
    private RadioButton mFemale;
    private RadioButton mDonor;
    private RadioButton mReceiver;
    private RadioGroup mGender;
    private RadioGroup mStatus;
    private String mLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        populateSpinner();
        mSpinner.setOnItemSelectedListener(this);
        String text = mSpinner.getSelectedItem().toString();
        mButton = findViewById(R.id.edit_profile_submit_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getText();
                Intent profintent = new Intent(EditProfile.this,Profile.class);
                startActivity(profintent);

            }
        });

    }

    private void populateSpinner() {
        mSpinner = (Spinner) findViewById(R.id.edit_profile_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mLabel = parent.getItemAtPosition(position).toString();
        getSpinnerItem(mLabel);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void getText (){
        mAge = findViewById(R.id.edit_profile_age);
        String age = getEditText(mAge);
        mEmail = findViewById(R.id.edit_profile_email);
        String email = getEditText(mEmail);
        mName = findViewById(R.id.edit_profile_name);
        String name = getEditText(mName);
        mNumber = findViewById(R.id.edit_profile_number);
        String number = getEditText(mNumber);
        String blood_group = getSpinnerItem(mLabel);
        mGender = findViewById(R.id.edit_profile_radio_group_gender);
        mStatus = findViewById(R.id.edit_profile_radio_group_status);
        mMale = findViewById(R.id.radioButtonMale);
        mFemale = findViewById(R.id.radioButtonFemale);
        mDonor = findViewById(R.id.radioButtonDonor);
        mReceiver = findViewById(R.id.radioButtonReceiver);
        String gender = getRadioButtonText(mGender);
        String status = getRadioButtonText(mStatus);

        ProfileOpenHelper mdb = new ProfileOpenHelper(this);
        mdb.insertData(name,email,number,age,blood_group,status,gender);



    }
    public String getRadioButtonText (RadioGroup radioGroup ){

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton =  findViewById(selectedId);
        String text = radioButton.getText().toString();
        return text;


    }
    public String getSpinnerItem(String text){
        String spinnerText = text;
        return text;
    }
    public String getEditText(EditText editText){
        String text = editText.getText().toString();
        return text;
    }
}
