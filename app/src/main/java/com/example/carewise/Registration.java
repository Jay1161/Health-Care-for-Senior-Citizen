package com.example.carewise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText email, password;
    private Button btnregister;
    private TextView tvloginp;
    private FirebaseAuth mAuth;
    private boolean passwordVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = findViewById(R.id.email);
//        fname = findViewById(R.id.fname);
//        lname = findViewById(R.id.lname);
        password = findViewById(R.id.password);
        btnregister = findViewById(R.id.btnregister);
        tvloginp = findViewById(R.id.tvloginp);

        mAuth = FirebaseAuth.getInstance();

        tvloginp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,Login.class));
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String txtFname = fname.getText().toString();
//                String txtLname = lname.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(Registration.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6){
                    Toast.makeText(Registration.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtEmail , txtPassword);
                }
            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = password.getSelectionEnd();
                        if (passwordVisibility){
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);

                            //for Hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisibility = false;
                        } else{
                            //show password here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);

                            //for Show password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisibility = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Registration.this, "Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this,MainActivity.class));
                }
                else{
                    Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
