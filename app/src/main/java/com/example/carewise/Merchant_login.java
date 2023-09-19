//package com.example.carewise;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class Merchant_login extends AppCompatActivity {
//
//    private Button btnlogin;
//    private EditText merusername, merpassword;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_merchant_login);
//
//        btnlogin = findViewById(R.id.btnlogin);
//        merusername = findViewById(R.id.merusername);
//        merpassword = findViewById(R.id.merpassword);
//
//    }
//}

package com.example.carewise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Merchant_login extends AppCompatActivity {

    private Button btnlogin;
    private EditText merusername, merpassword;

    String temppass, tempuser;
    private FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_login);

        btnlogin = findViewById(R.id.btnlogin);
        merusername = findViewById(R.id.merusername);
        merpassword = findViewById(R.id.merpassword);


        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("Admin");


//        Log.d("TAG1010", "onCreate: "+ databaseReference);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
//                        String  password = dataSnapshot.child("password").getValue();
                        temppass = dataSnapshot.child("password").getValue().toString();
                        tempuser = dataSnapshot.child("username").getValue().toString();
                        String edtuser = merusername.getText().toString();
                        String edtpass = merpassword.getText().toString();

                        if (edtuser.equals(tempuser) && edtpass.equals(temppass)) {
//                            Log.d("TAG1010", "Success: " + temppass + tempuser);
                            Intent intent = new Intent(Merchant_login.this, Merchant_main.class);
                            startActivity(intent);
                        } else {
//                            Log.d("TAG1010", "Fail: " + edtuser + edtpass);
                            Toast.makeText(Merchant_login.this, "Login failed. Check your credentials.", Toast.LENGTH_SHORT).show();

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG1010", "onCreate: " + e);

                    }
                });

            }
        });
/*
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = merusername.getText().toString().trim();
                String passwordText = merpassword.getText().toString().trim();

                if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText)) {
                    Toast.makeText(Merchant_login.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if the email address ends with "@carewise.com"
                if (emailText.endsWith("@carewise.com")) {
                    // You can proceed with Firebase Authentication
                    auth.signInWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener(Merchant_login.this, task -> {
                                if (task.isSuccessful()) {
                                    // Login successful
                                    Intent intent = new Intent(Merchant_login.this, Merchant_main.class);
                                    startActivity(intent);
                                } else {
                                    // Login failed
                                    Toast.makeText(Merchant_login.this, "Login failed. Check your credentials.", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    // Email address is not from "@carewise.com"
                    Toast.makeText(Merchant_login.this, "Email address must end with @carewise.com", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/


    }
}
