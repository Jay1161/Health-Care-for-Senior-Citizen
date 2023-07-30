package com.example.carewise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class AppointmentActivity extends AppCompatActivity {

    private Button btnappointment;
    private EditText Patient_name,Patient_age,email,number,address,Relative_name,Relative_number,problem,appointment_date,appointment_time;
//    private DocumentReference documentReference;
//    private FirebaseFirestore db;
    FirebaseAuth fauth;
    private DatabaseReference bookingsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        //Database Variable
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        bookingsRef = database.getReference("Bookings");

//        db = FirebaseFirestore.getInstance();
//
//        fauth = FirebaseAuth.getInstance();
//        String get = fauth.getCurrentUser().getEmail();

        btnappointment = findViewById(R.id.btnappointment);
        Patient_name = findViewById(R.id.Patient_name);
        Patient_age = findViewById(R.id.Patient_age);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        address = findViewById(R.id.address);
        Relative_name = findViewById(R.id.Relative_name);
        Relative_number = findViewById(R.id.Relative_number);
        problem = findViewById(R.id.problem);
        appointment_date = findViewById(R.id.appointment_date);
        appointment_time = findViewById(R.id.appointment_time);

        btnappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Patientname = Patient_name.getText().toString();
                String Patientage = Patient_age.getText().toString();
                String Patientemail = email.getText().toString();
                String Patientnumber = number.getText().toString();
                String Patientaddress = address.getText().toString();
                String Relativename = Relative_name.getText().toString();
                String Relativenumber = Relative_number.getText().toString();
                String Patientproblem = problem.getText().toString();
                String Date = appointment_date.getText().toString();
                String Time = appointment_time.getText().toString();

                if(Patientname.isEmpty() || Patientage.isEmpty() || Patientemail.isEmpty() || Patientnumber.isEmpty() || Patientaddress.isEmpty() || Relativename.isEmpty() || Relativenumber.isEmpty() || Patientproblem.isEmpty() || Date.isEmpty() || Time.isEmpty() ){
                    Toast.makeText(AppointmentActivity.this, "Please fill all details", Toast.LENGTH_LONG).show();
                }
                else {
                    HashMap<String,String> user = new HashMap<>();
                    user.put("Patient Name", Patientname);
                    user.put("Patient Age", Patientage);
                    user.put("Email", Patientemail);
                    user.put("Number", Patientnumber);
                    user.put("Address", Patientaddress);
                    user.put("Relative Name", Relativename);
                    user.put("Relative Number", Relativenumber);
                    user.put("Patient Problem", Patientproblem);
                    user.put("Date", Date);
                    user.put("Time", Time);

                    // Generate a unique key for the booking entry
                    String key = bookingsRef.push().getKey();

                    // Write the selected date and time under the generated key in the "bookings" node
                    bookingsRef.child(key).child("Name").setValue(Patientname);
                    bookingsRef.child(key).child("Patient age").setValue(Patientage);
                    bookingsRef.child(key).child("Email").setValue(Patientemail);
                    bookingsRef.child(key).child("Number").setValue(Patientnumber);
                    bookingsRef.child(key).child("Address").setValue(Patientaddress);
                    bookingsRef.child(key).child("Relative Name").setValue(Relativename);
                    bookingsRef.child(key).child("Relative Number").setValue(Relativenumber);
                    bookingsRef.child(key).child("Patient Problem").setValue(Patientproblem);
                    bookingsRef.child(key).child("Date").setValue(Date);
                    bookingsRef.child(key).child("Time").setValue(Time);

                }

                Toast.makeText(AppointmentActivity.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
