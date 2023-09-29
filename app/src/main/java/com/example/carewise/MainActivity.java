package com.example.carewise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.carewise.Fragment.AboutFragment;
import com.example.carewise.Fragment.DashboardFragment;
import com.example.carewise.Fragment.HomeFragment;
import com.example.carewise.Fragment.NotificationFragment;
import com.example.carewise.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView; // Add this line
    public static final String Shared_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav_drawer);
        bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment()).commit();

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item clicks here
                int itemId = item.getItemId(); // Get the selected item's ID

                if (itemId == R.id.theme) {
                    // Handle the "Theme" item click
                    Toast.makeText(MainActivity.this, "Theme clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.share) {
                    // Handle the "Share" item click
                    Toast.makeText(MainActivity.this, "Share clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.rate_us) {
                    // Handle the "Rate Us" item click
                    Toast.makeText(MainActivity.this, "Rate Us clicked", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.about) {
                    // Handle the "About" item click
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new AboutFragment()).commit();
                } else if (itemId == R.id.logout) {
                    // Handle the "Logout" item click

                    Toast.makeText(MainActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                }

                // Close the drawer after item click
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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