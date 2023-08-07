package com.example.carewise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.carewise.Fragment.AboutFragment;
import com.example.carewise.Fragment.DashboardFragment;
import com.example.carewise.Fragment.HomeFragment;
import com.example.carewise.Fragment.NotificationFragment;
import com.example.carewise.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment()).commit();

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {

        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.navigation_dashboard) {
            selectedFragment = new DashboardFragment();
        } else if (itemId == R.id.navigation_notifications) {
            selectedFragment = new NotificationFragment();
        } else if (itemId == R.id.navigation_aboutus) {
            selectedFragment = new AboutFragment();
        }else if (itemId == R.id.navigation_profile) {
            selectedFragment = new ProfileFragment();
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, selectedFragment).commit();
        }
        return true;
    };
}