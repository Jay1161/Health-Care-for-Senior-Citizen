package com.example.carewise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HealthInfoActivity extends AppCompatActivity {

    EditText fullName, age, bloodGroup, allergies, medicalCondition;
    RadioGroup genderGroup;
    RadioButton male, female;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

        fullName = findViewById(R.id.fname);
        age = findViewById(R.id.age);
        bloodGroup = findViewById(R.id.bloodgrp);
        allergies = findViewById(R.id.allergie);
        medicalCondition = findViewById(R.id.medcondition);

        genderGroup = findViewById(R.id.health_gender);
        male = findViewById(R.id.health_male);
        female = findViewById(R.id.health_female);

        submitButton = findViewById(R.id.btnsubmit);

        // Load existing data when the activity starts
        loadData();

        submitButton.setOnClickListener(v -> {
            // Save data when the Submit button is clicked
            saveData();
            Toast.makeText(HealthInfoActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
        });

    }

    private void saveData() {
        String fullNameValue = fullName.getText().toString().trim();
        String ageValue = age.getText().toString().trim();
        String bloodGroupValue = bloodGroup.getText().toString().trim();
        String allergiesValue = allergies.getText().toString().trim();
        String medicalConditionValue = medicalCondition.getText().toString().trim();

        // Get the selected gender
        String genderValue = "";
        int selectedId = genderGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.health_male) {
            genderValue = "Male";
        } else if (selectedId == R.id.health_female) {
            genderValue = "Female";
        }

        // Get an instance of SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("HealthInfo", MODE_PRIVATE);

        // Get an instance of SharedPreferences.Editor to edit the data
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the data with corresponding keys
        editor.putString("fullName", fullNameValue);
        editor.putString("age", ageValue);
        editor.putString("bloodGroup", bloodGroupValue);
        editor.putString("allergies", allergiesValue);
        editor.putString("medicalCondition", medicalConditionValue);
        editor.putString("gender", genderValue);

        // Commit the changes
        editor.apply();
    }

    private void loadData() {
        // Get an instance of SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("HealthInfo", MODE_PRIVATE);

        // Retrieve the data using the keys and set them to the respective EditText fields
        fullName.setText(sharedPreferences.getString("fullName", ""));
        age.setText(sharedPreferences.getString("age", ""));
        bloodGroup.setText(sharedPreferences.getString("bloodGroup", ""));
        allergies.setText(sharedPreferences.getString("allergies", ""));
        medicalCondition.setText(sharedPreferences.getString("medicalCondition", ""));

        // Set the radio button selection based on the saved gender value
        String genderValue = sharedPreferences.getString("gender", "");
        if (genderValue.equals("Male")) {
            male.setChecked(true);
        } else if (genderValue.equals("Female")) {
            female.setChecked(true);
        }
    }
}