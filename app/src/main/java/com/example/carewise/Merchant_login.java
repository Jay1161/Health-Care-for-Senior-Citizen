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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Merchant_login extends AppCompatActivity {

    private Button btnlogin;
    private EditText merusername, merpassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_login);

        btnlogin = findViewById(R.id.btnlogin);
        merusername = findViewById(R.id.merusername);
        merpassword = findViewById(R.id.merpassword);

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance();

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
    }
}
