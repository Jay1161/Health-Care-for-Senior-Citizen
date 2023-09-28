package com.example.carewise.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.carewise.AlaramActivity;
import com.example.carewise.AppointmentActivity;
import com.example.carewise.HealthInfoActivity;
import com.example.carewise.Login;
import com.example.carewise.R;
import com.example.carewise.ReachEmergencyActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Button reach_emerg, alarm, health_info, appointment,logout;
    View view;
    private FirebaseAuth mAuth;
    private MediaPlayer mediaPlayer;

    public static final String Shared_PREFS = "sharedPrefs";

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        reach_emerg = view.findViewById(R.id.reach_emerg);
        alarm = view.findViewById(R.id.alarm);
        health_info = view.findViewById(R.id.health_info);
        appointment = view.findViewById(R.id.appointment);
        logout = view.findViewById(R.id.btnlogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Shared_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("name","");
                editor.apply();

                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(getContext(), Login.class);
                startActivity(i);

                Toast.makeText(getContext(),"Logged out Successfully",Toast.LENGTH_LONG).show();
                Log.e("TAGLOG","LogOut");
            }

        });
        reach_emerg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(), ReachEmergencyActivity.class);
                startActivity(i);
            }
        });

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                // Initialize MediaPlayer
//                mediaPlayer = MediaPlayer.create(getContext(), R.raw.danger_alarm);
//
//                // Start playing the alarm sound
//                if (mediaPlayer != null) {
//                    mediaPlayer.start();
//                }

                // Add an intent to navigate to the alarm page
                Intent intent = new Intent(getContext(), AlaramActivity.class);
                startActivity(intent);

            }
        });
        health_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), HealthInfoActivity.class);
                startActivity(i);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AppointmentActivity.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}