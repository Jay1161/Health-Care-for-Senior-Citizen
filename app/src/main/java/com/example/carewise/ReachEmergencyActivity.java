package com.example.carewise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carewise.Adapter.EmergencyContactAdapter;
import com.example.carewise.Fragment.emergency_bottomsheet;
import com.example.carewise.model.EmergencyContact;

import java.util.ArrayList;
import java.util.List;

public class ReachEmergencyActivity extends AppCompatActivity {

    private Button btn_add_contact;
    private RecyclerView recyclerView;
    private EmergencyContactAdapter adapter;
    private List<EmergencyContact> emergencyContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reach_emergency);

        btn_add_contact = findViewById(R.id.btn_add_contact);

        // Initialize RecyclerView and Adapter
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new EmergencyContactAdapter(emergencyContacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                //Old Method
//                emergency_bottomsheet emergencyBottomsheet = new emergency_bottomsheet();
//                emergencyBottomsheet.show(getSupportFragmentManager(), emergencyBottomsheet.getTag());

                // Show the bottom sheet fragment to add a contact
                emergency_bottomsheet bottomSheetFragment = new emergency_bottomsheet();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

            }
        });
    }

    // Method to add a new emergency contact
    public void addEmergencyContact(String name, String phoneNumber) {
        EmergencyContact contact = new EmergencyContact(name, phoneNumber);
        emergencyContacts.add(contact);
        adapter.notifyItemInserted(emergencyContacts.size() - 1);
    }

}